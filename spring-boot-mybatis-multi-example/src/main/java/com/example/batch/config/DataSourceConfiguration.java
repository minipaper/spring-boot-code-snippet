package com.example.batch.config;

import com.example.batch.properties.BatchDataSourceProperties;
import com.example.batch.properties.DatasourceProperties;
import com.example.batch.properties.MysqlDataSourceProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

public abstract class DataSourceConfiguration {

    public abstract DataSource dataSource();

    abstract void initialize(org.apache.tomcat.jdbc.pool.DataSource dataSource);

    protected void configureDataSource(org.apache.tomcat.jdbc.pool.DataSource dataSource, DatasourceProperties datasourceProperties) {
        dataSource.setDriverClassName(datasourceProperties.getDriverClassName());
        dataSource.setUrl(datasourceProperties.getUrl());
        dataSource.setUsername(datasourceProperties.getUserName());
        dataSource.setPassword(datasourceProperties.getPassword());
        dataSource.setMaxActive(datasourceProperties.getMaxActive());
        dataSource.setMaxIdle(datasourceProperties.getMaxIdle());
        dataSource.setMinIdle(datasourceProperties.getMinIdle());
        dataSource.setMaxWait(datasourceProperties.getMaxWait());
        dataSource.setTestOnBorrow(false);
        dataSource.setTestOnReturn(false);

        if (datasourceProperties.isInitialize())
            initialize(dataSource);
    }
}

@Configuration
@EnableConfigurationProperties(BatchDataSourceProperties.class)
class BatchDatabaseConfig extends DataSourceConfiguration {

    @Autowired
    private BatchDataSourceProperties batchDataSourceProperties;

    @Override
    protected void initialize(org.apache.tomcat.jdbc.pool.DataSource dataSource) {
        PathMatchingResourcePatternResolver pathResolver = new PathMatchingResourcePatternResolver();
        // 초기 schema, data 추가
        Resource schema = pathResolver.getResource("classpath:scripts/batch-schema.sql");
        Resource data = pathResolver.getResource("classpath:scripts/batch-data.sql");
        DatabasePopulator databasePopulator = new ResourceDatabasePopulator(schema, data);
        DatabasePopulatorUtils.execute(databasePopulator, dataSource);
    }

    //    @Bean(name = "batchDataSource", destroyMethod = "close")
    @Bean(name = "batchDataSource")
    @Primary
    public DataSource dataSource() {
        org.apache.tomcat.jdbc.pool.DataSource batchDataSource = new org.apache.tomcat.jdbc.pool.DataSource();
        configureDataSource(batchDataSource, batchDataSourceProperties);
        return batchDataSource;
    }

    @Bean(name = "batchTransactionManager")
    @Primary
    public PlatformTransactionManager batchTransactionManager(@Qualifier("batchDataSource") DataSource batchDataSource) {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(batchDataSource);
        transactionManager.setGlobalRollbackOnParticipationFailure(false);
        return transactionManager;
    }
}

@Configuration
@EnableConfigurationProperties(MysqlDataSourceProperties.class)
class MysqlDatabaseConfig extends DataSourceConfiguration {

    @Autowired
    private MysqlDataSourceProperties mysqlDataSourceProperties;

    @Override
    protected void initialize(org.apache.tomcat.jdbc.pool.DataSource dataSource) {
        PathMatchingResourcePatternResolver pathResolver = new PathMatchingResourcePatternResolver();
        // 초기 schema, data 추가
        Resource schema = pathResolver.getResource("classpath:scripts/mysql-schema.sql");
        Resource data = pathResolver.getResource("classpath:scripts/mysql-data.sql");
        DatabasePopulator databasePopulator = new ResourceDatabasePopulator(schema, data);
        DatabasePopulatorUtils.execute(databasePopulator, dataSource);
    }

    //        @Bean(name = "batchDataSource", destroyMethod = "close")
    @Bean(name = "mysqlDataSource")
    public DataSource dataSource() {
        org.apache.tomcat.jdbc.pool.DataSource mysqlDataSource = new org.apache.tomcat.jdbc.pool.DataSource();
        configureDataSource(mysqlDataSource, mysqlDataSourceProperties);
        return mysqlDataSource;
    }

    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier("mysqlDataSource") DataSource mysqlDataSource) {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(mysqlDataSource);
        transactionManager.setGlobalRollbackOnParticipationFailure(false);
        return transactionManager;
    }
}