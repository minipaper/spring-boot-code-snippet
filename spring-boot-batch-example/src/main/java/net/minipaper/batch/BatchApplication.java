package net.minipaper.batch;

import lombok.extern.slf4j.Slf4j;
import net.minipaper.batch.schedule.Job1Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
@EnableScheduling
@SpringBootApplication
public class BatchApplication extends SpringBootServletInitializer implements CommandLineRunner {

    @Autowired
    Job1Scheduler job1Scheduler;

    public static void main(String[] args) {
        // Set Default JVM properties
        System.setProperty("java.net.preferIPv4Stack", "true");
        System.setProperty("file.encoding", "UTF-8");

        SpringApplication.run(BatchApplication.class, args);
    }


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(BatchApplication.class);
    }

    @Override
    public void run(String... args) {
        job1Scheduler.startScheduler();
    }
}
