package com.luo.jobx.core.rpc.service;

import com.luo.jobx.core.bean.ReturnX;
import com.luo.jobx.core.rpc.bean.TriggerParam;

/**
 * 任务执行 RPC服务
 *
 * @author xiangnan
 */
public interface TriggerService {

    ReturnX<String> run(TriggerParam param);

}
