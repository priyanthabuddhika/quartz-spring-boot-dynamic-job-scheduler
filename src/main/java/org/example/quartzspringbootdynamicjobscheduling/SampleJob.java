package org.example.quartzspringbootdynamicjobscheduling;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class SampleJob implements Job {
    private static final Logger logger = Logger.getLogger(SampleJob.class.getName());

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        logger.info(() -> jobExecutionContext.getJobDetail().getJobDataMap().getString("time"));
    }
}
