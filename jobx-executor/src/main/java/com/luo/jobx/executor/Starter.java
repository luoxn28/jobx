package com.luo.jobx.executor;

import com.luo.jobx.executor.component.ArgsComponent;
import com.xiaoleilu.hutool.http.HttpUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * jobx 执行器启动器
 *
 * @author xiangnan
 */
@Configuration
public class Starter implements CommandLineRunner {

    @Resource
    private ArgsComponent argsComponent;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("jobx 执行器启动啦...");

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
