package com.bestwu.mybatis.dao;

import com.bestwu.mybatis.entity.UserEntity;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * <br>
 *
 * @author Best Wu
 * @date 2021/3/26 21:23 <br>
 */
public class UserDAO extends AbstractDAO {

    public List<UserEntity> queryList() {
        SqlSession sqlSession = newSqlSession();
        List<UserEntity> list = sqlSession.selectList("com.bestwu.mybatis.mapper.UserMapper.queryList");
        sqlSession.close();
        return list;
    }

    public UserEntity queryById(Long userId) {
        SqlSession sqlSession = newSqlSession();
        UserEntity userEntity = sqlSession.selectOne("com.bestwu.mybatis.mapper.UserMapper.queryById", userId);
        sqlSession.close();
        return userEntity;
    }

    public UserEntity queryByNameAndPassword(UserEntity userEntity) {
        SqlSession sqlSession = newSqlSession();
        UserEntity user = sqlSession.selectOne("com.bestwu.mybatis.mapper.UserMapper.queryByNameAndPassword", userEntity);
        sqlSession.close();
        return user;
    }
}
