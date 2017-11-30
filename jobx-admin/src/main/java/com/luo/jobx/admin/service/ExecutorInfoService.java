package com.luo.jobx.admin.service;

import com.luo.jobx.core.bean.RegisterParam;
import com.luo.jobx.core.bean.RegisterResult;
import com.luo.jobx.core.bean.ReturnX;

/**
 * 执行器信息服务类
 *
 * @author xiangnan
 */
public interface ExecutorInfoService {

    ReturnX<String> register(RegisterParam param);

}
