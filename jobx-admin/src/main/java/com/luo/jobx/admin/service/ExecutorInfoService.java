package com.luo.jobx.admin.service;

import com.luo.jobx.admin.bean.ExecutorInfoBean;

import java.util.List;

/**
 * 执行器信息服务类
 *
 * @author xiangnan
 */
public interface ExecutorInfoService {

    List<ExecutorInfoBean> getExecutorList();

}
