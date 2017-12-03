package com.luo.jobx.admin.config;

import com.luo.jobx.admin.component.ExecutorStatusCheckTask;
import com.luo.jobx.admin.component.JobxTrigger;
import com.luo.jobx.admin.component.bean.JobxBean;
import com.luo.jobx.admin.dao.ExecutorInfoDao;
import org.quartz.Scheduler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;

/**
 * StarterConfig
 *
 * @author xiangnan
 */
@Configuration
public class StarterConfig {

    /**
     * 执行器状态检测线程
     */
    @Bean
    public ExecutorStatusCheckTask executorStatusCheckTask(ExecutorInfoDao executorDao) {
        ExecutorStatusCheckTask executorStatusCheckTask = new ExecutorStatusCheckTask(executorDao);

        executorStatusCheckTask.startCheck();
        return executorStatusCheckTask;
    }

    /**
     * quartz配置
     */
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(DataSource dataSource) {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();

        schedulerFactoryBean.setDataSource(dataSource);
        schedulerFactoryBean.setAutoStartup(true);
        schedulerFactoryBean.setStartupDelay(20);
        schedulerFactoryBean.setOverwriteExistingJobs(true);
        schedulerFactoryBean.setApplicationContextSchedulerContextKey("applicationContextKey");
        schedulerFactoryBean.setConfigLocation(new ClassPathResource("quartz.properties"));

        return schedulerFactoryBean;
    }

    @Bean(name = "internalScheduler")
    public Scheduler scheduler(SchedulerFactoryBean schedulerFactoryBean) {
        return schedulerFactoryBean.getScheduler();
    }

    /**
     * 需要先注入JobxTrigger供JobxBean使用，666
     */
    @Bean
    public JobxBean jobxBean(JobxTrigger jobxTrigger) {
        return new  JobxBean(jobxTrigger);
    }

}
