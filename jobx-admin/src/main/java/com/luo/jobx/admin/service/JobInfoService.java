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

    String[][] getJobType();

}
