package com.luo.jobx.admin.service.impl;

import com.luo.jobx.admin.bean.JobInfoBean;
import com.luo.jobx.admin.component.JobxScheduler;
import com.luo.jobx.admin.convert.JobInfoConvert;
import com.luo.jobx.admin.dao.JobInfoDao;
import com.luo.jobx.admin.entity.JobInfoEntity;
import com.luo.jobx.admin.exception.JobInfoException;
import com.luo.jobx.admin.exception.enums.JobInfoEnum;
import com.luo.jobx.admin.service.JobInfoService;
import com.luo.jobx.core.util.BusinessIDGenerator;
import com.luo.jobx.core.util.R;
import com.xiaoleilu.hutool.util.StrUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class JobInfoServiceImpl implements JobInfoService {

    private final static Logger logger = LogManager.getLogger();

    private static final String[][] JOB_TYPE = new String[][] {
            {R.jobType.Script, "脚本类型"},
            {R.jobType.Java, "Java类型"}
    };

    @Resource
    private JobInfoDao jobDao;

    @Resource
    private JobxScheduler jobxScheduler;

    @Override
    public List<JobInfoBean> getJobList() {
        return JobInfoConvert.toBeanList(jobDao.selectList(null));
    }

    @Override
    public String[][] getJobType() {
        return JOB_TYPE;
    }

    @Override
    @Transactional
    public int addJob(JobInfoBean jobBean) {
        JobInfoEntity jobEntity = JobInfoConvert.toEntity(jobBean);
        if (jobEntity == null) {
            throw new JobInfoException(JobInfoEnum.PARAM_ERROR);
        }

        // 根据任务名确认该任务是否已存在
        if (jobDao.selectByJobName(jobEntity.getJobName()) != null) {
            logger.info("任务已存在, jobName: {}", jobEntity.getJobName());
            throw new JobInfoException(JobInfoEnum.JOB_EXIST);
        }

        jobEntity.setJobId(BusinessIDGenerator.getId());
        jobEntity.setCreateTime(new Date());
        jobEntity.setUpdateTime(new Date());
        jobEntity.setStatus(R.jobStatus.CREATED);
        if (StrUtil.isBlank(jobEntity.getCron())) {
            jobEntity.setJobRole(R.jobRole.Child);
        } else {
            jobEntity.setJobRole(R.jobRole.Normal);
        }

        try {
            jobDao.insert(jobEntity);

            if (StrUtil.isNotBlank(jobEntity.getCron())) {
                jobxScheduler.addJob(jobEntity.getJobId(), jobEntity.getJobId(), jobEntity.getCron());

                // 更新任务状态为运行
                jobEntity = new JobInfoEntity(jobEntity.getJobId());
                jobEntity.setStatus(R.jobStatus.RUNNING);
                jobDao.update(jobEntity);
            }
            logger.info("添加任务成功, jobId: {}, jobName: {}", jobEntity.getJobId(), jobEntity.getJobName());
        } catch (Exception e) {
            logger.info("添加任务失败, jobId: {}, jobName: {}", jobEntity.getJobId(), jobEntity.getJobName());
            throw new JobInfoException(JobInfoEnum.UNKNOWN_ERROR, e.getMessage());
        }

        return 1;
    }

    @Override
    public int triggerJob(String jobId) {
        JobInfoEntity entity = jobDao.selectByJobId(jobId);
        if (entity == null) {
            logger.warn("触发任务失败，该任务不存在，jobId: {}", jobId);
            return -1;
        }

        try {
            if (StrUtil.isNotBlank(entity.getCron())) {
                jobxScheduler.triggerJob(entity.getJobId(), entity.getJobId());
                logger.info("触发任务成功，jobId: " + jobId);
            }
            return 1;
        } catch (Exception e) {
            logger.info("触发任务失败, jobId: {}, jobName: {}, {}", entity.getJobId(), entity.getJobName(), e);
            throw new JobInfoException(JobInfoEnum.UNKNOWN_ERROR, e.getMessage());
        }
    }

    @Override
    @Transactional
    public int resumeJob(String jobId) {
        JobInfoEntity entity = jobDao.selectByJobId(jobId);
        if (entity == null) {
            logger.warn("恢复任务失败，该任务不存在，jobId: {}", jobId);
            return -1;
        }

        try {
            entity.setStatus(R.jobStatus.RUNNING);
            jobDao.update(entity);

            if (StrUtil.isNotBlank(entity.getCron())) {
                jobxScheduler.resumeJob(entity.getJobId(), entity.getJobId());
            }

            logger.info("恢复任务成功，jobId: {}", jobId);
            return 1;
        } catch (Exception e) {
            logger.info("恢复任务失败, jobId: {}, jobName: {}, {}", entity.getJobId(), entity.getJobName(), e);
            throw new JobInfoException(JobInfoEnum.UNKNOWN_ERROR, e.getMessage());
        }
    }

    @Override
    @Transactional
    public int pauseJob(String jobId) {
        JobInfoEntity entity = jobDao.selectByJobId(jobId);
        if (entity == null) {
            logger.warn("暂停任务失败，该任务不存在，jobId: {}", jobId);
            return -1;
        }

        try {
            entity.setStatus(R.jobStatus.PAUSE);
            jobDao.update(entity);

            if (StrUtil.isNotBlank(entity.getCron())) {
                jobxScheduler.pauseJob(entity.getJobId(), entity.getJobId());
            }

            logger.info("暂停任务成功，jobId:{}", jobId);
            return 1;
        } catch (Exception e) {
            logger.info("暂停任务失败, jobId: {}, jobName: {}, {}", entity.getJobId(), entity.getJobName(), e);
            throw new JobInfoException(JobInfoEnum.UNKNOWN_ERROR, e.getMessage());
        }
    }

    @Override
    @Transactional
    public int deleteJob(String jobId) {
        JobInfoEntity entity = jobDao.selectByJobId(jobId);
        if (entity == null) {
            logger.warn("删除任务失败，该任务不存在，jobId: {}", jobId);
            return -1;
        }

        try {
            entity.setStatus(R.jobStatus.DELETED);
            jobDao.update(entity);

            if (StrUtil.isNotBlank(entity.getCron())) {
                jobxScheduler.deleteJob(entity.getJobId(), entity.getJobId());
            }

            logger.info("删除任务成功，jobId: {}", jobId);
            return 1;
        } catch (Exception e) {
            logger.info("删除任务失败, jobId: {}, jobName: {}, {}", entity.getJobId(), entity.getJobName(), e);
            throw new JobInfoException(JobInfoEnum.UNKNOWN_ERROR, e.getMessage());
        }
    }

}
