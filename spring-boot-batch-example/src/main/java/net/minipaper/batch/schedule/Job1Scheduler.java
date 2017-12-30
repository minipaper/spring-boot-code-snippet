package net.minipaper.batch.schedule;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Data
@Component("JOB1")
public class Job1Scheduler extends DynamicAbstractScheduler {
    private static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    // default 10초
    private String cron = "*/10 * * * * ?";

    @Override
    public Trigger getTrigger() {
        return new CronTrigger(cron);
    }

    @Override
    public void exec() {
        log.info("Job1Scheduler {}", DATE_FORMAT.format(new Date()));
    }

}
