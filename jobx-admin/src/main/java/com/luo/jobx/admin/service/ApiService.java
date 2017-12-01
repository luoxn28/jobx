package com.luo.jobx.admin.service;

import com.luo.jobx.core.bean.RegisterParam;
import com.luo.jobx.core.bean.ReturnX;

/**
 * 执行器同信API服务类
 *
 * @author xiangnan
 */
public interface ApiService {

    ReturnX<String> register(RegisterParam param);

    ReturnX<String> keepAlive(String ip, int port);

}
