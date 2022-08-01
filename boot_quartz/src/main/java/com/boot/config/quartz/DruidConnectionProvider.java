package com.boot.config.quartz;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.quartz.utils.ConnectionProvider;

import java.sql.Connection;

/**
 * @ClassName DruidDataSourceConfig.java
 * @Author Xuhui
 * @Date 2022/7/15 14:38
 * @Description Druid连接池的Quartz扩展类，作用：帮助Quartz创建连接池
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DruidConnectionProvider implements ConnectionProvider {


/*
#============================================================================
# JDBC
#============================================================================
org.quartz.jobqzDS.connectionProvider.class:com.zking.q03.quartz.DruidConnectionProvider
org.quartz.dataSourStore.driverDelegateClass:org.quartz.impl.jdbcjobstore.StdJDBCDelegate
org.quartz.jobStore.useProperties:false
org.quartz.jobStore.dataSource:qzDS
#org.quartz.dataSource.qzDS.connectionProvider.class:org.quartz.utils.PoolingConnectionProvider
org.quartz.dataSource.ce.qzDS.driver:com.mysql.jdbc.Driver
org.quartz.dataSource.qzDS.URL:jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=UTF-8
org.quartz.dataSource.qzDS.user:root
org.quartz.dataSource.qzDS.password:root
org.quartz.dataSource.qzDS.maxConnections:30
org.quartz.dataSource.qzDS.validationQuery: select 0
*/

    /**
     * 常量配置与quartz.properties文件的key保持一致(去掉前缀)
     * 同时提供set方法,Quartz框架自动注入值.
     */

    /**
     * JDBC驱动
     */
    public String driver;
    /**
     * JDBC连接串
     */
    public String URL;
    /**
     * 数据库用户名
     */
    public String user;
    /**
     * 数据库用户密码
     */
    public String password;
    /**
     * 数据库最大连接数
     */
    public int maxConnection;
    /**
     * 数据库SQL查询每次连接返回执行到连接池,以确保它仍然是有效的
     */
    public String validationQuery;
    private boolean validateOnCheckout;
    private int idleConnectionValidationSeconds;
    public String maxCachedStatementsPerConnection;
    private String discardIdleConnectionsSeconds;
    public static final int DEFAULT_DB_MAX_CONNECTIONS = 10;
    public static final int DEFAULT_DB_MAX_CACHED_STATEMENTS_PER_CONNECTION = 120;
    /**
     * Druid连接池
     */
    private DruidDataSource datasource;

    @Override
    @SneakyThrows
    public Connection getConnection() {
        return datasource.getConnection();
    }

    @Override
    public void shutdown() {
        datasource.close();
    }

    @Override
    @SneakyThrows
    public void initialize() {
        assert this.URL != null : "DB URL cannot be null";
        assert this.driver != null : "DB driver class name cannot be null!";
        assert this.maxConnection > 0 : "Max connections must be greater than zero!";

        datasource = new DruidDataSource();
        datasource.setDriverClassName(this.driver);
        datasource.setUrl(this.URL);
        datasource.setUsername(this.user);
        datasource.setPassword(this.password);
        datasource.setMaxActive(this.maxConnection);
        datasource.setMinIdle(1);
        datasource.setMaxWait(0);
        datasource.setMaxPoolPreparedStatementPerConnectionSize(DruidConnectionProvider.DEFAULT_DB_MAX_CACHED_STATEMENTS_PER_CONNECTION);
        if (this.validationQuery != null) {
            datasource.setValidationQuery(this.validationQuery);
            if (!this.validateOnCheckout) {
                datasource.setTestOnReturn(true);
            } else {
                datasource.setTestOnBorrow(true);
            }
            datasource.setValidationQueryTimeout(this.idleConnectionValidationSeconds);
        }
    }


}
