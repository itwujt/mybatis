package com.bestwu.jdbc;

import com.alibaba.druid.pool.DruidDataSource;
import com.bestwu.jdbc.configuration.DruidConfiguration;
import com.bestwu.jdbc.dao.UserDAO;
import com.bestwu.jdbc.util.ExecutorUtil;

import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * <br>
 *
 * @author Best Wu
 * @date 2021/3/26 20:31 <br>
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor executor = ExecutorUtil.newCachedExecutor(20, 50);
        UserDAO userDAO = new UserDAO();
        for (int i = 0; i < 100; i++) {
            try {
                executor.execute(() -> {
                    long start = System.currentTimeMillis();
                    userDAO.queryList();
                    System.out.println(System.currentTimeMillis() - start);
                });
            } catch (RejectedExecutionException e) {
                TimeUnit.SECONDS.sleep(3);
            }
        }
        /*executor.execute(() -> {
            userDAO.queryList();
        });
        TimeUnit.SECONDS.sleep(1);*/
        DruidDataSource dataSource = (DruidDataSource) DruidConfiguration.getInstance().getDataSource();
        System.out.println(dataSource);
    }
}
