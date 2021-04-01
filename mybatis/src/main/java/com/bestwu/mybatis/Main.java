package com.bestwu.mybatis;

import com.bestwu.mybatis.configuration.MybatisConfiguration;
import com.bestwu.mybatis.dao.UserDAO;
import com.bestwu.mybatis.entity.UserEntity;
import com.bestwu.mybatis.mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * <br>
 *
 * @author Best Wu
 * @date 2021/3/26 20:34 <br>
 */
public class Main {

    public static void main(String[] args) {
        /*SqlSessionFactory sqlSessionFactory = MybatisConfiguration.getInstance().getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<UserEntity> userEntities = mapper.queryList();
        for (UserEntity userEntity : userEntities) {
            System.out.println(userEntity);
        }*/

        UserDAO userDAO = new UserDAO();
        List<UserEntity> userEntities = userDAO.queryList();
        for (UserEntity userEntity : userEntities) {
            System.out.println(userEntity);
        }

        UserEntity userEntity = userDAO.queryById(1L);
        System.out.println(userEntity);

        UserEntity user = userDAO.queryByNameAndPassword(new UserEntity().setUsername("wujt").setPassword("123456"));
        System.out.println(user);
    }
}
