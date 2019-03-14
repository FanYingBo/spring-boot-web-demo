package com.study.selfs.gupao.springboot.jdbc.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
public class TranscationManagerConfigration {

    @Bean
    @Primary
    public DataSourceTransactionManager masterDataSourceTransactionManager(@Qualifier("masterDataSource") DataSource masterDataSource){
        return new DataSourceTransactionManager(masterDataSource);
    }
    @Bean
    public DataSourceTransactionManager slaveDataSourceTransactionManager(@Qualifier("slaveDataSource") DataSource slaveDataSource){
        return new DataSourceTransactionManager(slaveDataSource);
    }
}
