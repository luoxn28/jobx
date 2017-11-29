package com.luo.jobx.core.thread;

import com.luo.jobx.core.bean.RegisterParam;
import com.luo.jobx.core.bean.ReturnX;
import com.luo.jobx.core.util.HttpXUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 执行器注册线程
 *
 * @author xiangnan
 */
@Component
public class RegisterThread {

    private final static Logger logger = LogManager.getLogger(RegisterThread.class);

    private boolean running = true;

    public void start(String registerUrl, RegisterParam param) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (running) {
                    ReturnX result = HttpXUtil.register(registerUrl, param);
                    if (!result.isSuccess()) {
                        logger.info("执行器注册失败，注册url: " + registerUrl);
                    }

                    try {
                        TimeUnit.SECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        logger.error(e);
                    }
                }
            }
        });

        // 启动注册线程
        thread.start();
    }

    public void stop() {
        this.running = false;
    }

}
