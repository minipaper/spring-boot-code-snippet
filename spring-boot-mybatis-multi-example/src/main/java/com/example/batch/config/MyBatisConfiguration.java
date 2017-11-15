package com.example.batch.config;

import com.example.batch.repository.support.BatchSchema;
import com.example.batch.repository.support.MysqlSchema;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;

public abstract class MyBatisConfiguration {
    public static final String BASE_PACKAGE_PREFIX = "com.example.batch.repository";

    public static final String ENTITY_PACKAGE_PREFIX = "com.example.batch.repository.domain";

    public static final String CONFIG_LOCATION_PATH = "classpath:mybatis/mybatis-config.xml";

    public static final String MAPPER_LOCATIONS_PATH = "classpath:mybatis/mapper/**/*.xml";

    protected void configureSqlSessionFactory(SqlSessionFactoryBean sessionFactoryBean, DataSource dataSource) throws IOException {
        PathMatchingResourcePatternResolver pathResolver = new PathMatchingResourcePatternResolver();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setTypeAliasesPackage(ENTITY_PACKAGE_PREFIX);
        sessionFactoryBean.setConfigLocation(pathResolver.getResource(CONFIG_LOCATION_PATH));
        sessionFactoryBean.setMapperLocations(pathResolver.getResources(MAPPER_LOCATIONS_PATH));
    }
}

@Configuration
@MapperScan(basePackages = MyBatisConfiguration.BASE_PACKAGE_PREFIX, annotationClass = BatchSchema.class, sqlSessionFactoryRef = "batchSqlSessionFactory")
@Import(value = {BatchDatabaseConfig.class})
class BatchMybatisConfig extends MyBatisConfiguration {

    @Bean(name = "batchSqlSessionFactory")
    @Primary
    public SqlSessionFactory batchSqlSessionFactory(@Qualifier("batchDataSource") DataSource batchDataSource) throws Exception {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        configureSqlSessionFactory(sessionFactoryBean, batchDataSource);
        return sessionFactoryBean.getObject();
    }
}


@Configuration
@MapperScan(basePackages = MyBatisConfiguration.BASE_PACKAGE_PREFIX, annotationClass = MysqlSchema.class, sqlSessionFactoryRef = "mysqlSqlSessionFactory")
@Import(value = {MysqlDatabaseConfig.class})
class MysqlMybatisConfig extends MyBatisConfiguration {

    @Bean(name = "mysqlSqlSessionFactory")
    public SqlSessionFactory mysqlSqlSessionFactory(@Qualifier("mysqlDataSource") DataSource mysqlDataSource) throws Exception {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        configureSqlSessionFactory(sessionFactoryBean, mysqlDataSource);
        return sessionFactoryBean.getObject();
    }
}