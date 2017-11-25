package com.luo.jobx.executor;

import com.luo.jobx.core.executor.ExecutorStarter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * jobx 执行器启动器
 *
 * @author xiangnan
 */
@Configuration
@ComponentScan(basePackages = {"com.luo.jobx.core"})
public class Starter implements CommandLineRunner {

    @Resource
    private ExecutorStarter executorStarter;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("jobx 执行器启动啦...");

        executorStarter.run(args);
    }

}
