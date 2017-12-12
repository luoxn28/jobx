package com.luo.jobx.admin.component;

import com.luo.jobx.admin.dao.JobInfoDao;
import com.luo.jobx.admin.entity.JobInfoEntity;
import com.luo.jobx.core.bean.ReturnX;
import com.luo.jobx.core.rpc.RpcClientProxy;
import com.luo.jobx.core.rpc.bean.TriggerParam;
import com.luo.jobx.core.rpc.service.TriggerService;
import com.xiaoleilu.hutool.date.DateUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * jobx 任务触发器
 *
 * @author xiangnan
 */
@Component
public class JobxTrigger {

    private final static Logger logger = LogManager.getLogger();

    @Resource
    private JobInfoDao jobDao;

    public void trigger(JobExecutionContext context) {
        // quartz调度任务的时间
        Date scheduledTime = context.getScheduledFireTime();
        JobKey jobKey = context.getTrigger().getJobKey();

        logger.info("quartz trigger job, jobKey: {}, time: {}", jobKey,
                DateUtil.format(scheduledTime, DateUtil.NORM_DATETIME_PATTERN));
        trigger(jobKey.getName(), jobKey.getGroup(), scheduledTime);
    }

    private void trigger(String jobId, String groupId, Date scheduledTime) {

        JobInfoEntity jobEntity = jobDao.selectByJobId(jobId);
        if (jobEntity == null) {
            logger.error("未找到任务，jobId=" + jobId);
        }

        TriggerService trigger = null;
        try {
            trigger = (TriggerService)
                    new RpcClientProxy(TriggerService.class, "http://127.0.0.1:9999").getObject();
            ReturnX<String> returnX= trigger.run(new TriggerParam("hello", "world"));
            System.out.println(returnX);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
