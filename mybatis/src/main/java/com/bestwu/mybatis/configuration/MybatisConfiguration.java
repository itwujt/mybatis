package com.bestwu.mybatis.configuration;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Best Wu
 * @date 2021/3/23 17:25 <br>
 */
public class MybatisConfiguration {

    private final SqlSessionFactory sqlSessionFactory;

    private MybatisConfiguration(){
        this.sqlSessionFactory = sqlSessionFactory();
    }

    public SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }

    private static class Holder {
        private final static MybatisConfiguration INSTANCE = new MybatisConfiguration();
    }

    public static MybatisConfiguration getInstance() {
        return Holder.INSTANCE;
    }


    private SqlSessionFactory sqlSessionFactory() {
        try {
            InputStream in = Resources.getResourceAsStream("mybatis/mybatis-config.xml");
            return new SqlSessionFactoryBuilder().build(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
