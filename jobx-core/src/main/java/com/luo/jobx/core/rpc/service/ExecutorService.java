package com.luo.jobx.core.rpc.service;

import com.luo.jobx.core.bean.ExecutorInfo;

/**
 * 执行器服务类，用于调用中心更新执行器信息
 *
 * @author xiangnan
 */
public interface ExecutorService {

    boolean updateExecutorInfo(ExecutorInfo executorInfo);

}
