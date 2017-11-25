package com.luo.jobx.core.executor;

import com.luo.jobx.core.component.ArgsComponent;
import com.xiaoleilu.hutool.http.HttpUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;

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

        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("ip", "127.0.0.1");
        paramMap.put("port", "9999");
        paramMap.put("name", "executor");

        String result= HttpUtil.post(args[2], paramMap);
        System.out.println(result);
    }

}