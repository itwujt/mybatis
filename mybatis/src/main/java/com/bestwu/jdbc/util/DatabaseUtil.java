package com.bestwu.jdbc.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Best Wu
 * @date 2021/3/24 8:38 <br>
 */
public class DatabaseUtil {

    /**
     * 释放资源
     * @param resultSet resultSet
     * @param statement statement
     * @param connection connection
     */
    public static void releaseResource(ResultSet resultSet, Statement statement, Connection connection) {
        try {
            if (null != resultSet) {
                resultSet.close();
            }
            if (null != statement) {
                statement.close();
            }
            if (null != connection) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            resultSet = null;
            statement = null;
            connection = null;
        }

    }
}
