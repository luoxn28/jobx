package com.test.jobx.core;

import com.luo.jobx.core.executor.ExecutorStarter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * 执行器启动测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestApplication.class})
public class ExecutorStarterTest {

    @Resource
    private ExecutorStarter executorStarter;

    @Test
    public void testRun() {
        for (int i = 0; i < 100; i++) {
            System.out.println();
            System.out.println();
        }
        System.out.println(executorStarter);
        for (int i = 0; i < 100; i++) {
            System.out.println();
            System.out.println();
        }
    }

}
