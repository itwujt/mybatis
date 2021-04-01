package com.bestwu.mybatis.dao;

import com.bestwu.mybatis.configuration.MybatisConfiguration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.TransactionIsolationLevel;

/**
 * <br>
 *
 * @author Best Wu
 * @date 2021/3/26 21:11 <br>
 */
public abstract class AbstractDAO {

    private final SqlSessionFactory sqlSessionFactory = MybatisConfiguration.getInstance().getSqlSessionFactory();
    /**
     * 默认事务的隔离级别是 读提交
     */
    private final static TransactionIsolationLevel DEFAULT_TX_LEVEL = TransactionIsolationLevel.READ_COMMITTED;

    /**
     * 开启一个 sql 会话
     * @return org.apache.ibatis.session.SqlSession
     */
    protected SqlSession newSqlSession() {
        return sqlSessionFactory.openSession(true);
    }

    /**
     * 开启事务
     * @param level 事务隔离级别
     * @return org.apache.ibatis.session.SqlSession
     */
    protected SqlSession openTx(TransactionIsolationLevel level) {
        return sqlSessionFactory.openSession(null != level ? level : DEFAULT_TX_LEVEL);
    }

    /**
     * 事务回滚
     * @param sqlSession sql 会话
     */
    protected void rollbackTx(SqlSession sqlSession) {
        sqlSession.rollback();
    }

    /**
     * 提交事务
     * @param sqlSession sql 会话
     */
    protected void commitTx(SqlSession sqlSession) {
        sqlSession.commit();
    }


}
