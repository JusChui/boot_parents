package com.boot.config.dataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * @ClassName slaverDataSourceConfig.java
 * @Author Xuhui
 * @Date 2022/7/27 15:22
 * @Description
 */
@Configuration
@MapperScan(basePackages = {"com.boot.slaver.**.mapper", "com.boot.slaver.**.dao"},
        sqlSessionFactoryRef = "slaverSqlSessionFactory")
public class SlaverDataSourceConfig {


    //@Value("${spring.datasource.slaver.mapper-location}")
    private static String MAPPER_LOCATION = "classpath:com/boot/slaver/**/*.xml";

    /**
     * 主数据源，Primary注解必须增加，它表示该数据源为默认数据源
     * 项目中还可能存在其他的数据源，如获取时不指定名称，则默认获取这个数据源，如果不添加，则启动时候回报错
     */
    @Primary
    @Bean(name = "slaverDataSource")
    // 读取spring.datasource.slaver前缀的配置文件映射成对应的配置对象
    @ConfigurationProperties(prefix = "spring.datasource.slaver")
    public DataSource dataSource() {
        DataSource build = DataSourceBuilder.create().build();
        return build;
    }

    /**
     * 事务管理器，Primary注解作用同上
     */
    @Bean(name = "slaverTransactionManager")
    @Primary
    public PlatformTransactionManager dataSourceTransactionManager(@Qualifier("slaverDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * session工厂，Primary注解作用同上
     */

    @Bean(name = "slaverSqlSessionFactory")
    @Primary
    public SqlSessionFactory sqlSessionFactory(@Qualifier("slaverDataSource") DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(SlaverDataSourceConfig.MAPPER_LOCATION));
        return sessionFactoryBean.getObject();
    }

}
