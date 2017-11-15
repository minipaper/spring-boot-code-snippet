package com.example.batch.config;

import javax.sql.DataSource;

import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.annotation.BatchConfigurer;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.support.JobRegistryBeanPostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BatchConfiguration {
	
	@Autowired
	@Qualifier("batchDataSource")
	private DataSource dataSource;


	// 배치히스토리 적용할 db 설정
	@Bean
    public BatchConfigurer batchConfig() {
        return new DefaultBatchConfigurer(dataSource);
    }
	
	@Bean
	public JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor(JobRegistry jobRegistry) {
	    JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor = new JobRegistryBeanPostProcessor();
	    jobRegistryBeanPostProcessor.setJobRegistry(jobRegistry);
	    return jobRegistryBeanPostProcessor;
	}
}
