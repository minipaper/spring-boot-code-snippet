package net.minipaper.batch.schedule;

import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

public abstract class DynamicAbstractScheduler {
    private ThreadPoolTaskScheduler scheduler;

    public void startScheduler() {
        stopScheduler();
        scheduler = new ThreadPoolTaskScheduler();
        scheduler.initialize();
        scheduler.schedule(() -> exec(), getTrigger());
    }

    public void changeTrigger(Trigger trigger) {
        stopScheduler();
        scheduler = new ThreadPoolTaskScheduler();
        scheduler.initialize();
        scheduler.schedule(() -> exec(), trigger);
    }

    public void stopScheduler() {
        if (null != scheduler) {
            scheduler.shutdown();
        }
    }

    public abstract Trigger getTrigger();

    public abstract void exec();
}
