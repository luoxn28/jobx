package com.luo.jobx.executor;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

/**
 * jobx 执行器启动器
 *
 * @author xiangnan
 */
@Configuration
public class Starter implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        System.out.println("jobx 执行器启动啦...");
    }

}
