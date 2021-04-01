package com.bestwu.jdbc.properties;

import lombok.Data;

/**
 * @author Best Wu
 * @date 2021/3/23 14:34 <br>
 */
@Data
public class DruidProperties {
    /**
     * 初始化时建立物理连接的个数。<br>
     * 初始化发生在显示调用init方法，或者第一次getConnection时 <br>
     */
    private Integer initialSize;
    /**
     * 最小连接池数量
     */
    private Integer minIdle;
    /**
     * 最大连接池数量
     */
    private Integer maxActive;
    /**
     * 获取连接时最大等待时间，单位毫秒。<br>
     * 配置了maxWait之后，缺省启用公平锁，并发效率会有所下降，<br>
     * 如果需要可以通过配置useUnfairLock属性为true使用非公平锁。<br>
     */
    private Integer maxWait;
    /**
     * 是否缓存preparedStatement，也就是PSCache。<br>
     * PSCache对支持游标的数据库性能提升巨大，比如说oracle。在mysql下建议关闭。<br>
     */
    private Boolean poolPreparedStatements;
    /**
     * 要启用PSCache，必须配置大于0，当大于0时，poolPreparedStatements自动触发修改为true。<br>
     * 在Druid中，不会存在Oracle下PSCache占用内存过多的问题，可以把这个数值配置大一些，比如说100 <br>
     */
    private Integer maxPoolPreparedStatementPerConnectionSize;
    /**
     * 有两个含义：<br>
     * 1) Destroy线程会检测连接的间隔时间，如果连接空闲时间大于等于minEvictableIdleTimeMillis则关闭物理连接。<br>
     * 2) testWhileIdle的判断依据，详细看testWhileIdle属性的说明 <br>
     */
    private Integer timeBetweenEvictionRunsMillis;
    /**
     * 连接保持空闲而不被驱逐的最小时间
     */
    private Integer minEvictableIdleTimeMillis;
    /**
     * 用来检测连接是否有效的sql，要求是一个查询语句，常用select 'x'。<br>
     * 如果validationQuery为null，testOnBorrow、testOnReturn、testWhileIdle都不会起作用。<br>
     */
    private String validationQuery;
    /**
     * 申请连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能。
     */
    private Boolean testOnBorrow;
    /**
     * 归还连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能。
     */
    private Boolean testOnReturn;
    /**
     * 建议配置为true，不影响性能，并且保证安全性。<br>
     * 申请连接的时候检测，如果空闲时间大于timeBetweenEvictionRunsMillis，执行validationQuery检测连接是否有效。<br>
     */
    private Boolean testWhileIdle;

    private String connectionProperties;
    /**
     * 属性类型是字符串，通过别名的方式配置扩展插件，常用的插件有：<br>
     * 监控统计用的filter:stat <br>
     * 日志用的filter:log4j <br>
     * 防御sql注入的filter:wall <br>
     */
    private String filters;
    /**
     * 连接池中的minIdle数量以内的连接，空闲时间超过minEvictableIdleTimeMillis，则会执行keepAlive操作。
     */
    private Boolean keepAlive;
}
