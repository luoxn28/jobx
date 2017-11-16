package com.luo.jobx.executor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * jobx 执行器
 * @author xiangnan
 */
@SpringBootApplication
public class ExecutorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExecutorApplication.class, args);
    }

    @Bean
    public Starter starter() {
        return new Starter();
    }

}
