package net.minipaper.batch.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Component
public class SimpleScheduler {

    private static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    // 60초마다 실행
    @Scheduled(fixedRate = 60 * 1000)
    public void simplePrint() {
        log.info("60초에 한번씩 실행 {}", DATE_FORMAT.format(new Date()));
    }
}
