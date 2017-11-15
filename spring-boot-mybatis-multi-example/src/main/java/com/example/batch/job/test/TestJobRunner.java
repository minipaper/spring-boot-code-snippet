package com.example.batch.job.test;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.batch.repository.BatchMapper;

@Component
public class TestJobRunner {

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	@Autowired
	private JobRegistry jobRegistry;

	@Autowired
	private JobLauncher launcher;

//	@Autowired
//	private BatchMapper batchMapper;

	@Scheduled(cron = "0/10 * * * * *")
	public void job() {
		System.out.println("[TestJobRunner Test] " + dateFormat.format(new Date()));
		this.runBatch();
		System.out.println("[TestJobRunner Test END] " + dateFormat.format(new Date()));
		// batchMapper.findAll().forEach(System.out::println);

	}

	public String runBatch() {
		try {
			Job job = jobRegistry.getJob(TestJobConfiguration.JOB_NAME);
			
			 JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
			 jobParametersBuilder.addDate("date", new Date());
			JobExecution jobExecution = launcher.run(job, jobParametersBuilder.toJobParameters());
			System.out.println("jobExecution ->" + jobExecution.getStatus());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Something went wrong");
		}
		return "OK";
	}

}
