package com.luo.jobx.admin.service.impl;

import com.luo.jobx.admin.bean.JobInfoBean;
import com.luo.jobx.admin.convert.JobInfoConvert;
import com.luo.jobx.admin.dao.JobInfoDao;
import com.luo.jobx.admin.service.JobInfoService;
import com.luo.jobx.core.util.R;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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

}
