package com.luo.jobx.admin.component.bean;

import com.luo.jobx.admin.component.JobxTrigger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * jobx任务执行类
 *
 * @author xiangnan
 */
@Component
public class JobxBean extends QuartzJobBean {

    private static JobxTrigger jobxTrigger;

    public JobxBean() { }
    public JobxBean(JobxTrigger jobxTrigger) {
        this.jobxTrigger = jobxTrigger;
    }

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        jobxTrigger.trigger(context);
    }

}
