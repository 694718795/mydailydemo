package com.shu;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * @description:
 * @author: shurunlong
 * @create: 2020-10-22 15:15
 */
// Druid配置
@Configuration
public class DruidDataSourceConfig {
    @Primary
    @Qualifier("primaryDataSource")
    @Bean(name = "primaryDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.druid.primary")
    public DataSource primaryDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Qualifier("secondaryDataSource")
    @Bean(name = "secondaryDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.druid.secondary")
    public DataSource secondaryDataSource() {
        return DruidDataSourceBuilder.create().build();
    }
}
