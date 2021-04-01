package com.bestwu.jdbc.dao;

import com.bestwu.jdbc.entity.UserEntity;
import com.bestwu.jdbc.util.DatabaseUtil;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Best Wu
 * @date 2021/3/24 8:21 <br>
 */
@Slf4j
public class UserDAO extends AbstractDAO {


    public final static String SQL_QUERY_USER_LIST =
            "select * from user where is_enabled = 1";

    public final static String SQL_INERT_USER =
            "insert into user";

    public final static String SQL_UPDATE_USER_BY_USER_ID =
            "update user set password = ? where user_id = ?";


    public List<UserEntity> queryList() {
        List<UserEntity> list = new LinkedList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(SQL_QUERY_USER_LIST);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                UserEntity userEntity = objectMapper(resultSet);
                list.add(userEntity);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.releaseResource(resultSet, preparedStatement, connection);
        }
        return Collections.emptyList();
    }

    public UserEntity insertSelective(UserEntity userEntity) {
        userEntity.setCreateTime(LocalDateTime.now());
        userEntity.setEnableFlag(true);
        int placeHolder = 2;
        String sql = SQL_INERT_USER + "(";
        String username = userEntity.getUsername();
        if (null != username && !"".equals(username)) {
            sql += "username, ";
            placeHolder++;
        }
        String password = userEntity.getPassword();
        if (null != password && !"".equals(password)) {
            sql += "password, ";
            placeHolder++;
        }
        LocalDateTime createTime = userEntity.getCreateTime();
        Boolean enableFlag = userEntity.getEnableFlag();
        sql += "create_time, is_enabled) values (";
        for (int i = 0; i < placeHolder; i++) {
            if (i == placeHolder - 1) {
                sql += "?";
                break;
            }
            sql += "?, ";
        }
        sql += ")";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();
            log.info("insert sql：{}", sql);
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            boolean assignUsername = false;
            boolean assignPassword = false;
            boolean assignCreateTime = false;
            boolean assignEnableFlag = false;
            for (int i = 0; i < placeHolder; i++) {
                if (null != username && !"".equals(username) && !assignUsername) {
                    preparedStatement.setString(i + 1, username);
                    assignUsername = true;
                    continue;
                }
                if (null != password && !"".equals(password) && !assignPassword) {
                    preparedStatement.setString(i + 1, password);
                    assignPassword = true;
                    continue;
                }
                if (null != createTime && !assignCreateTime) {
                    preparedStatement.setTimestamp(i + 1, Timestamp.valueOf(createTime));
                    assignCreateTime = true;
                    continue;
                }
                if (null != enableFlag && !assignEnableFlag) {
                    preparedStatement.setBoolean(i + 1, enableFlag);
                    assignEnableFlag = true;
                }
            }
            int rows = preparedStatement.executeUpdate();
            if (rows == 1) {
                resultSet = preparedStatement.getGeneratedKeys();
                resultSet.next();
                return userEntity.setUserId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.releaseResource(null, preparedStatement, connection);
        }
        return null;
    }

    public int updateUser(UserEntity userEntity) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Savepoint savepoint = null;
        try {
            connection = getConnection();
            savepoint = openTx(connection, 2, "", false);
            preparedStatement = connection.prepareStatement(SQL_UPDATE_USER_BY_USER_ID);
            preparedStatement.setString(1, userEntity.getPassword());
            preparedStatement.setLong(2, userEntity.getUserId());
            int rows = preparedStatement.executeUpdate();
            int i = 1 / 0;
            return rows;
        } catch (Exception e) {
            log.error("Update user failed!", e);
            txRollback(connection, savepoint);
            log.info("tx rollback!");
        } finally {
            if (null != connection) {
                commitTx(connection);
            }
            DatabaseUtil.releaseResource(null, preparedStatement, connection);
        }
        return 0;
    }




    private UserEntity objectMapper(ResultSet resultSet) throws SQLException {
        long userId = resultSet.getLong("user_id");
        String username = resultSet.getString("username");
        String password = resultSet.getString("password");
        long creatorId = resultSet.getLong("creator_id");
        Timestamp createTime = resultSet.getTimestamp("create_time");
        long modifierId = resultSet.getLong("modifier_id");
        Timestamp modifyTime = resultSet.getTimestamp("modify_time");
        boolean enableFlag = resultSet.getBoolean("is_enabled");
        return new UserEntity().setUserId(userId).setUsername(username).setPassword(password)
                .setCreatorId(creatorId).setCreateTime(null == createTime ? null : createTime.toLocalDateTime())
                .setModifierId(modifierId).setCreateTime(null == modifyTime ? null : modifyTime.toLocalDateTime())
                .setEnableFlag(enableFlag);
    }

}
