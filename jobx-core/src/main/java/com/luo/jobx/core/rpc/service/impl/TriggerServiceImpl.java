package com.luo.jobx.core.rpc.service.impl;

import com.luo.jobx.core.bean.ReturnX;
import com.luo.jobx.core.rpc.bean.TriggerParam;
import com.luo.jobx.core.rpc.service.TriggerService;

public class TriggerServiceImpl implements TriggerService {

    @Override
    public ReturnX<String> run(TriggerParam param) {

        System.out.println(param);

        return ReturnX.SUCCESS;
    }

}
