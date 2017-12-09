package com.luo.jobx.admin.service.impl;

import com.luo.jobx.admin.bean.JobInfoBean;
import com.luo.jobx.admin.convert.JobInfoConvert;
import com.luo.jobx.admin.dao.JobInfoDao;
import com.luo.jobx.admin.entity.JobInfoEntity;
import com.luo.jobx.admin.exception.JobInfoException;
import com.luo.jobx.admin.exception.enums.JobInfoEnum;
import com.luo.jobx.admin.service.JobInfoService;
import com.luo.jobx.core.util.BusinessIDGenerator;
import com.luo.jobx.core.util.R;
import com.xiaoleilu.hutool.util.StrUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class JobInfoServiceImpl implements JobInfoService {

    private final static Logger logger = LogManager.getLogger(JobInfoServiceImpl.class);

    private static final String[][] JOB_TYPE = new String[][] {
            {R.jobType.Script, "脚本类型"},
            {R.jobType.Java, "Java类型"}
    };

    @Resource
    private JobInfoDao jobDao;

    @Override
    public List<JobInfoBean> getJobList() {
        return JobInfoConvert.toBeanList(jobDao.selectList(null));
    }

    @Override
    public String[][] getJobType() {
        return JOB_TYPE;
    }

    @Override
    public int addJob(JobInfoBean jobBean) {
        JobInfoEntity jobEntity = JobInfoConvert.toEntity(jobBean);
        if (jobEntity == null) {
            throw new JobInfoException(JobInfoEnum.PARAM_ERROR);
        }

        // 根据任务名确认该任务是否已存在
        if (jobDao.selectByJobName(jobEntity.getJobName()) != null) {
            logger.info("任务已存在, jobName: " + jobEntity.getJobName());
            throw new JobInfoException(JobInfoEnum.JOB_EXIST);
        }

        jobEntity.setJobId(BusinessIDGenerator.getId());
        jobEntity.setCreateTime(new Date());
        jobEntity.setUpdateTime(new Date());
        jobEntity.setStatus(R.jobStatus.CREATED);
        if (StrUtil.isBlank(jobEntity.getCron())) {
            jobEntity.setJobRole(R.jobRole.Child);
        }

        jobDao.insert(jobEntity);
        logger.info("添加任务成功, jobId: " + jobEntity.getJobId() + ", jobName: " + jobEntity.getJobName());

        return 1;
    }

    @Override
    public int deleteJob(String jobId) {
        JobInfoEntity entity = new JobInfoEntity();

        entity.setJobId(jobId);
        entity.setStatus(R.jobStatus.DELETED);
        jobDao.update(entity);

        logger.info("删除任务成功，jobId: " + jobId);
        return 1;
    }

}
