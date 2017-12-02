package com.luo.jobx.admin.config;

import com.luo.jobx.admin.component.ExecutorStatusCheckTask;
import com.luo.jobx.admin.dao.ExecutorInfoDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * StarterConfig
 *
 * @author xiangnan
 */
@Configuration
public class StarterConfig {

    @Bean
    public ExecutorStatusCheckTask executorStatusCheckTask(ExecutorInfoDao executorDao) {
        ExecutorStatusCheckTask executorStatusCheckTask = new ExecutorStatusCheckTask(executorDao);

        executorStatusCheckTask.startCheck();
        return executorStatusCheckTask;
    }

}
