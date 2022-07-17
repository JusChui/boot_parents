package com.boot.config.mybatis;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @File: MybatisPlusPageConifg.java
 * @Author: JusChui
 * @CreateTime: 2022-07-17 11:03:09
 * @Description: mybatis plus分页配置
 */
@Configuration
@MapperScan({"com.boot.**.mapper", "com.boot.**.dao"})
public class MybatisPlusPageConfig {

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor interceptor = new PaginationInterceptor();
        interceptor.setLimit(500L);
        interceptor.setOverflow(false);
        interceptor.setCountSqlParser(new JsqlParserCountOptimize(false));
        return interceptor;
    }
}
