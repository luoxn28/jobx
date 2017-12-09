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

    /**
     * 添加任务
     * @param jobBean 任务信息
     * @return true成功/false失败
     */
    int addJob(JobInfoBean jobBean);

    /**
     * 删除任务
     * @param jobId jobId
     * @return 1成功
     */
    int deleteJob(String jobId);

}
