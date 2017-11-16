package com.luo.jobx.executor;

import org.springframework.boot.CommandLineRunner;

/**
 * jobx 执行器启动器
 * @author xiangnan
 */
public class Starter implements CommandLineRunner {

    @Override
    public void run(String... strings) throws Exception {
        System.out.println("jobx 执行器启动啦...");
    }

}
