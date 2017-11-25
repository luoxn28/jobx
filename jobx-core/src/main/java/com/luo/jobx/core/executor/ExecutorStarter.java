package com.luo.jobx.core.executor;

import com.luo.jobx.core.bean.RegisterParam;
import com.luo.jobx.core.component.ArgsComponent;
import com.luo.jobx.core.util.HttpXUtil;
import com.xiaoleilu.hutool.convert.Convert;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 执行器启动器
 *
 * @author xiangnan
 */
@Component
public class ExecutorStarter {

    @Resource
    private ArgsComponent argsComponent;

    public void run(String... args) throws Exception {

        // 参数检查
        argsComponent.check(args);

        String ip = args[0];
        int port = Convert.toInt(args[1]);
        String registerUrl = args[2];

        RegisterParam param = new RegisterParam(ip, port);
        System.out.println(HttpXUtil.register(registerUrl, param));
    }

}