package com.test.jobx.core;

import com.luo.jobx.core.executor.ExecutorStarter;
import com.luo.jobx.core.rpc.RpcClientProxy;
import com.luo.jobx.core.rpc.bean.TriggerParam;
import com.luo.jobx.core.rpc.service.TriggerService;
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

        try {
            TriggerService triggerService = (TriggerService) new RpcClientProxy(
                    "http://127.0.0.1:9999", TriggerService.class).getObject();

            triggerService.run(new TriggerParam("111", "222"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
