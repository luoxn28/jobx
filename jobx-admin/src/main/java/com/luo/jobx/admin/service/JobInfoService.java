package com.luo.jobx.admin.service;

import com.luo.jobx.admin.bean.JobInfoBean;

import java.util.List;

/**
 * 任务信息服务类
 *
 * @author xiangnan
 */
public interface JobInfoService {

    List<JobInfoBean> getJobList();

    /**
     * 获取任务类型
     *
     * @return 任务类型列表
     */
    String[][] getJobType();

    boolean insert(JobInfoBean jobBean);

}
