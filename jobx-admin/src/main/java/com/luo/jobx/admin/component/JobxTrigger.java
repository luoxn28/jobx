package com.luo.jobx.admin.component;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * jobx 任务触发器
 *
 * @author xiangnan
 */
@Component
public class JobxTrigger {

    private final static Logger logger = LogManager.getLogger(JobxTrigger.class);

    public void trigger(JobExecutionContext context) {
        // quartz调度任务的时间
        Date scheduledTime = context.getScheduledFireTime();
        JobKey jobKey = context.getTrigger().getJobKey();

        trigger(jobKey.getName(), jobKey.getGroup(), scheduledTime);
    }

    private void trigger(String jobId, String groupId, Date scheduledTime) {
        System.out.println("任务被调度了");
        System.out.println(jobId);
        System.out.println(groupId);
        System.out.println(scheduledTime);
    }

}
