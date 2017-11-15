package com.example.batch.job.test;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.MyBatisBatchItemWriter;
import org.mybatis.spring.batch.MyBatisPagingItemReader;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.batch.repository.domain.DatabaseInfo;

@Configuration
@EnableBatchProcessing
public class TestJobConfiguration {

	public static final String JOB_NAME = "TEST_JOB";
	private static final String STEP_NAME = "TEST_JOB_STEP_01";
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");


	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private SqlSessionFactory mysqlSqlSessionFactory;

	@Bean
	public Job job() {
		return jobBuilderFactory.get(JOB_NAME).start(step()).build();
	}

	@Bean
	public Step step() {
		return stepBuilderFactory.get(STEP_NAME).<DatabaseInfo, DatabaseInfo>chunk(1).reader(sampleItemReader())
				 .processor(sampleItemProcessor())
				.writer(sampleItemWriter()).build();
	}

	@Bean
	public ItemReader<DatabaseInfo> sampleItemReader() {
		MyBatisPagingItemReader<DatabaseInfo> reader = new MyBatisPagingItemReader<DatabaseInfo>();
		reader.setSqlSessionFactory(mysqlSqlSessionFactory);
		reader.setQueryId("com.example.batch.repository.MysqlMapper.findAll");

		return reader;
	}

	 @Bean
	 public ItemProcessor<DatabaseInfo, DatabaseInfo> sampleItemProcessor() {
	 return new ItemProcessor<DatabaseInfo, DatabaseInfo>() {
		
		@Override
		public DatabaseInfo process(DatabaseInfo item) throws Exception {
			item.setDescription(dateFormat.format(new Date()));
			return item;
		}
	};
	 }

	@Bean
	public ItemWriter<DatabaseInfo> sampleItemWriter() {
		MyBatisBatchItemWriter<DatabaseInfo> writer = new MyBatisBatchItemWriter<DatabaseInfo>();
		writer.setSqlSessionFactory(mysqlSqlSessionFactory);
		writer.setStatementId("com.example.batch.repository.MysqlMapper.update");

		return writer;
	}

}
