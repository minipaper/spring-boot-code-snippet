package net.minipaper.batch.controller;

import net.minipaper.batch.common.ContextProvider;
import net.minipaper.batch.domain.job.JobRepository;
import net.minipaper.batch.schedule.DynamicAbstractScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.support.CronSequenceGenerator;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class SampleController {
    private static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    JobRepository jobRepository;

    @GetMapping("/")
    public ResponseEntity<?> hello() {
        return ResponseEntity.ok("Hello World");
    }

    @GetMapping("/list")
    public ResponseEntity<?> getJobNames() {
        return ResponseEntity.ok(jobRepository.findAll());
    }

    @PostMapping("/cron/check")
    public ResponseEntity<?> checkCronString(@RequestParam String cron, @RequestParam(required = false, defaultValue = "5") int nextDateCnt) {

        Map<String, Object> resultMap = new HashMap<>();
        boolean isSuccess = false;
        try {
            CronSequenceGenerator cronSequenceGenerator = new CronSequenceGenerator(cron);

            Date nextExec = new Date();
            List<String> nextExecList = new ArrayList<>();
            for (int i = 0; i < nextDateCnt; i++) {
                nextExec = cronSequenceGenerator.next(nextExec);
                nextExecList.add(DATE_FORMAT.format(nextExec));
            }

            resultMap.put("nextExecList", nextExecList);
            isSuccess = true;
        } catch (IllegalArgumentException e) {
            resultMap.put("message", e.getMessage());
        }

        resultMap.put("success", isSuccess);

        return ResponseEntity.ok(resultMap);
    }

    @GetMapping("/stop/job1")
    public ResponseEntity<?> stopJob1() {
        DynamicAbstractScheduler scheduler = (DynamicAbstractScheduler) ContextProvider.getBean("JOB1");
        scheduler.stopScheduler();
        return ResponseEntity.ok("Stopped");
    }

    @GetMapping("/change/job1")
    public ResponseEntity<?> changeJob1() {
        DynamicAbstractScheduler scheduler = (DynamicAbstractScheduler) ContextProvider.getBean("JOB1");
        String cron = "*/5 * * * * ?";
        scheduler.changeTrigger(new CronTrigger(cron));
        return ResponseEntity.ok("Changed");
    }

}
