package com.study.selfs.gupao.springboot.jdbc.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfigration {

    /**
     * spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver
     * spring.datasource.url=jdbc:mysql://192.168.8.156:3306/devlopment?useSSL=false&serverTimezone=UTC&characterEncoding=utf8&allowMultiQueries=true
     * spring.datasource.username=root
     * spring.datasource.password=root123
     * @return
     */
    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.primary")
    public DataSource masterDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.secondary")
    public DataSource slaveDataSource(){
        return  DataSourceBuilder.create().build();
    }


}
