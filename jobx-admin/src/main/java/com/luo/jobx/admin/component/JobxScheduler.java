package com.luo.jobx.admin.component;

import com.luo.jobx.admin.component.bean.JobxBean;
import com.luo.jobx.core.exception.ExceptionX;
import com.luo.jobx.core.exception.JobException;
import com.luo.jobx.core.exception.ParamException;
import com.xiaoleilu.hutool.util.StrUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.quartz.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * jobx 调度器
 *
 * @author xiangnan
 */
@Component
public class JobxScheduler {

    private final static Logger logger = LogManager.getLogger(JobxScheduler.class);

    @Resource(name = "internalScheduler")
    private Scheduler scheduler;

    public void addJob(String name, String group, String cron) throws Exception {
        if (StrUtil.isBlank(name) || StrUtil.isBlank(group) || StrUtil.isBlank(cron)) {
            throw new ParamException(ExceptionX.ERROR_PARAM);
        }

        checkExists(name, group);

        // cron
        CronScheduleBuilder cronScheduleBuilder =
                CronScheduleBuilder.cronSchedule(cron).withMisfireHandlingInstructionDoNothing();
        CronTrigger cronTrigger =
                TriggerBuilder.newTrigger().withIdentity(name, group).withSchedule(cronScheduleBuilder).build();

        // 添加任务
        JobDetail jobDetail = JobBuilder.newJob(JobxBean.class).withIdentity(name, group).build();
        scheduler.scheduleJob(jobDetail, cronTrigger);

        logger.info("添加任务成功: jobDetail=" + jobDetail);
    }

    private void checkExists(String name, String group) throws Exception {
        if (scheduler.checkExists(new TriggerKey(name, group))) {
            throw new JobException(ExceptionX.JOB_EXISTS);
        }
    }

}
