package com.bestwu.jdbc.dao;

import com.alibaba.druid.sql.parser.Lexer;
import com.bestwu.jdbc.configuration.DruidConfiguration;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.sql.*;

/**
 * @author Best Wu
 * @date 2021/3/24 8:23 <br>
 */
@Slf4j
public abstract class AbstractDAO {

    private final DataSource dataSource = DruidConfiguration.getInstance().getDataSource();

    protected Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            log.error("Get Connection failed!", e);
        }
        return null;
    }

    protected Savepoint openTx(Connection connection, int txLevel, String savepoint, boolean isReadOnly) {
        try {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(txLevel);
            connection.setReadOnly(isReadOnly);
            if (null != savepoint && !"".equals(savepoint)) {
                return connection.setSavepoint(savepoint);
            }
            return connection.setSavepoint();
        } catch (SQLException e) {
            log.error("Open transaction failed!", e);
        }
        return null;
    }

    protected void commitTx(Connection connection) {
        try {
            connection.commit();
        } catch (SQLException e) {
            log.error("Transaction commit failed!", e);
        }
    }

    protected void txRollback(Connection connection, Savepoint savepoint) {
        try {
            if (null != savepoint) {
                connection.rollback(savepoint);
                return;
            }
            connection.rollback();
        } catch (SQLException e) {
            log.error("Transaction rollback failed!", e);
        }
    }


}
