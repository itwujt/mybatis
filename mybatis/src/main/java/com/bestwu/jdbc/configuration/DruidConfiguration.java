package com.bestwu.jdbc.configuration;

import com.alibaba.druid.DbType;
import com.alibaba.druid.pool.DruidDataSource;
import com.bestwu.jdbc.properties.DruidProperties;
import com.bestwu.jdbc.util.PropertiesUtil;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author Best Wu
 * @date 2021/3/23 14:36 <br>
 */
@Slf4j
public class DruidConfiguration {

    private final DataSource dataSource;

    public DataSource getDataSource() {
        return this.dataSource;
    }

    private DruidConfiguration(){
        dataSource = getDruidDataSource();
    }

    private static class Holder {
        private final static DruidConfiguration INSTANCE = new DruidConfiguration();
    }

    public static DruidConfiguration getInstance() {
        return Holder.INSTANCE;
    }

    private DruidProperties getDruidProperties(String resourcePath) {
        Class<DruidProperties> druidPropertiesClass = DruidProperties.class;
        boolean usePathParam = null == resourcePath || "".equals(resourcePath);
        InputStream inputStream = druidPropertiesClass.getClassLoader().getResourceAsStream(usePathParam ? "druid/druid.properties" : resourcePath);
        Properties properties = new Properties();
        DruidProperties druidProperties = null;
        try {
            if (null == inputStream) {
                throw new RuntimeException("Resource not find");
            }
            Reader reader = new BufferedReader(new InputStreamReader(inputStream, "GBK"));
            properties.load(reader);
            druidProperties = PropertiesUtil.getProps(properties, new DruidProperties(), "druid");
            log.info("Druid properties load success!");
        } catch (Exception e) {
            log.error("Druid properties load failed!", e);
        }
        return druidProperties;
    }


    private DruidDataSource getDruidDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDbType(DbType.mysql);
        DruidProperties druidProperties = getDruidProperties(null);
        druidDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        druidDataSource.setUrl("jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT%2B8");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("root");
        druidDataSource.setInitialSize(druidProperties.getInitialSize());
        druidDataSource.setMinIdle(druidProperties.getMinIdle());
        druidDataSource.setMaxActive(druidProperties.getMaxActive());
        druidDataSource.setMaxWait(druidProperties.getMaxWait());
        druidDataSource.setPoolPreparedStatements(druidProperties.getPoolPreparedStatements());
        druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(druidProperties.getMaxPoolPreparedStatementPerConnectionSize());
        druidDataSource.setTimeBetweenEvictionRunsMillis(druidProperties.getTimeBetweenEvictionRunsMillis());
        druidDataSource.setMinEvictableIdleTimeMillis(druidProperties.getMinEvictableIdleTimeMillis());
        druidDataSource.setValidationQuery(druidProperties.getValidationQuery());
        druidDataSource.setTestOnBorrow(druidProperties.getTestOnBorrow());
        druidDataSource.setTestOnReturn(druidProperties.getTestOnReturn());
        druidDataSource.setTestWhileIdle(druidProperties.getTestWhileIdle());
        druidDataSource.setConnectionProperties(druidProperties.getConnectionProperties());
        /*try {
            druidDataSource.setFilters(druidProperties.getFilters());
        } catch (SQLException e) {
            log.error("Druid DataSource init failed!", e);
        }*/
        druidDataSource.setKeepAlive(druidProperties.getKeepAlive());
        try {
            druidDataSource.init();
            log.info("Druid DataSource init success!");
        } catch (SQLException e) {
            log.error("Druid DataSource init failed!", e);
        }
        return druidDataSource;
    }



}
