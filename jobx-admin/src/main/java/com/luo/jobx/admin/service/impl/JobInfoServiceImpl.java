package com.luo.jobx.admin.service.impl;

import com.luo.jobx.admin.bean.JobInfoBean;
import com.luo.jobx.admin.convert.JobInfoConvert;
import com.luo.jobx.admin.dao.JobInfoDao;
import com.luo.jobx.admin.entity.JobInfoEntity;
import com.luo.jobx.admin.service.JobInfoService;
import com.luo.jobx.core.util.BusinessIDGenerator;
import com.luo.jobx.core.util.R;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class JobInfoServiceImpl implements JobInfoService {

    @Resource
    private JobInfoDao jobInfoDao;

    @Override
    public List<JobInfoBean> getJobList() {
        return JobInfoConvert.toBeanList(jobInfoDao.selectList(null));
    }

    @Override
    public String[][] getJobType() {
        return new String[][] {
                {R.jobType.Script, "脚本类型"},
                {R.jobType.Java, "Java类型"}
        };
    }

    @Override
    public boolean insert(JobInfoBean jobBean) {
        JobInfoEntity jobEntity = JobInfoConvert.toEntity(jobBean);
        if (jobBean == null) {
            return false;
        }

        jobEntity.setJobId(BusinessIDGenerator.getId());
        jobEntity.setJobRole("role");
        jobEntity.setCreateTime(new Date());
        jobEntity.setUpdateTime(new Date());
        jobEntity.setStatus(R.jobStatus.CREATED);

        try {
            jobInfoDao.insert(jobEntity);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
