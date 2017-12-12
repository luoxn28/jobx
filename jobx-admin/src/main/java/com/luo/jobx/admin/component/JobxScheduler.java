package com.luo.jobx.admin.component;

import com.luo.jobx.admin.component.bean.JobxBean;
import com.luo.jobx.admin.exception.JobInfoException;
import com.luo.jobx.admin.exception.enums.JobInfoEnum;
import com.xiaoleilu.hutool.util.StrUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * jobx 调度器
 *
 * @author xiangnan
 */
@Component
public class JobxScheduler {

    private final static Logger logger = LogManager.getLogger();

    @Resource(name = "internalScheduler")
    private Scheduler scheduler;

    /**
     * 添加任务
     * @param name jobId
     * @param group jobId
     * @param cron cron表达式
     */
    public void addJob(String name, String group, String cron) {
        if (StrUtil.isBlank(name) || StrUtil.isBlank(group) || StrUtil.isBlank(cron)) {
            throw new JobInfoException(JobInfoEnum.PARAM_ERROR);
        }

        try {
            if (checkExists(name, group)) {
                return;
            }

            // cron
            CronScheduleBuilder cronScheduleBuilder =
                    CronScheduleBuilder.cronSchedule(cron).withMisfireHandlingInstructionDoNothing();
            CronTrigger cronTrigger =
                    TriggerBuilder.newTrigger().withIdentity(name, group).withSchedule(cronScheduleBuilder).build();

            // 添加任务
            JobDetail jobDetail = JobBuilder.newJob(JobxBean.class).withIdentity(name, group).build();
            scheduler.scheduleJob(jobDetail, cronTrigger);

            logger.info("添加任务成功: jobDetail: {}", jobDetail);
        } catch (Exception e) {
            if (e instanceof SchedulerException) {
                logger.info("添加任务失败, quartz未知错误: name: {}, cron: {}", name, cron);
                throw new JobInfoException(JobInfoEnum.UNKNOWN_ERROR);
            } else {
                logger.info("添加任务失败, cron表达式错误: name: {}, cron: {}", name, cron);
                throw new JobInfoException(JobInfoEnum.CRON_ERROR);
            }
        }

    }

    /**
     * 触发任务
     * @param name jobId
     * @param group jobId
     */
    public void triggerJob(String name, String group) {
        try {
            if (checkExists(name, group)) {
                JobKey jobKey = new JobKey(name, group);
                scheduler.triggerJob(jobKey);
                logger.info("触发任务成功，triggerKey: {}", jobKey);
            } else {
                logger.warn("触发任务失败，未找到该任务，name: {}, group: {}", name, group);
            }
        } catch (Exception e) {
            logger.info("触发任务失败, Quartz错误: " + e);
            throw new JobInfoException(JobInfoEnum.QUARTZ_ERROR);
        }
    }

    /**
     * 暂定任务
     * @param name jobId
     * @param group jobId
     */
    public void pauseJob(String name, String group) {
        try {
            if (checkExists(name, group)) {
                JobKey jobKey = new JobKey(name, group);
                scheduler.pauseJob(jobKey);
                logger.info("暂停任务成功，triggerKey: {}", jobKey);
            } else {
                logger.warn("暂停任务失败，未找到该任务，name: {}, group: {}", name, group);
            }
        } catch (Exception e) {
            logger.info("暂停任务失败, Quartz错误: " + e);
            throw new JobInfoException(JobInfoEnum.QUARTZ_ERROR);
        }
    }

    /**
     * 恢复任务
     * @param name jobId
     * @param group jobIdS
     */
    public void resumeJob(String name, String group) {
        try {
            if (checkExists(name, group)) {
                JobKey jobKey = new JobKey(name, group);
                scheduler.resumeJob(jobKey);
                logger.info("恢复任务成功，triggerKey: {}", jobKey);
            } else {
                logger.warn("恢复任务失败，未找到该任务，name: {}, group: {}", name, group);
            }
        } catch (Exception e) {
            logger.info("恢复务失败, Quartz错误: " + e);
            throw new JobInfoException(JobInfoEnum.QUARTZ_ERROR);
        }
    }

    /**
     * 删除任务
     * @param name jobId
     * @param group jobId
     * @throws Exception quartz异常
     */
    public void deleteJob(String name, String group) {
        try {
            if (checkExists(name, group)) {
                TriggerKey triggerKey = new TriggerKey(name, group);
                scheduler.unscheduleJob(triggerKey);
                logger.info("删除任务成功，triggerKey: {}", triggerKey);
            } else {
                logger.warn("删除任务失败，未找到该任务，name: {}, group: {}", name, group);
            }
        } catch (Exception e) {
            logger.info("删除任务失败, Quartz错误: {}", e);
            throw new JobInfoException(JobInfoEnum.QUARTZ_ERROR);
        }
    }

    private boolean checkExists(String name, String group) throws Exception {
        return scheduler.checkExists(new TriggerKey(name, group));
    }

}
