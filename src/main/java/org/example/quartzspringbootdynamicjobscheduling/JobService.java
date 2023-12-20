package org.example.quartzspringbootdynamicjobscheduling;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

@Service
public class JobService {
    private static final Logger logger = Logger.getLogger(JobService.class.getName());

    private final Scheduler scheduler;

    public JobService(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    void createJob(final String id, final Integer frequencyInSeconds) {
        logger.log(Level.INFO, "Job ID : {}", id);
        try {
            final JobDetail job = newJob(SampleJob.class)
                    .withIdentity("job-" + id)
                    .usingJobData("time", Instant.now().toString())
                    .storeDurably()
                    .build();

            final Trigger trigger = newTrigger()
                    .withIdentity("trigger-job-" + id)
                    .withSchedule(
                            simpleSchedule()
                                    .withIntervalInSeconds(frequencyInSeconds)
                                    .repeatForever())
                    .build();

            scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException e) {
            logger.severe(e.getMessage());
        }
    }
}