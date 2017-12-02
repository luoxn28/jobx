package com.luo.jobx.core.thread;

import com.luo.jobx.core.bean.RegisterParam;
import com.luo.jobx.core.bean.RegisterResult;
import com.luo.jobx.core.bean.ReturnX;
import com.luo.jobx.core.util.HttpXUtil;
import com.luo.jobx.core.util.JacksonUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.Date;
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

                // 是否在保活状态
                boolean keepAlive = false;
                String keepAliveUrl = registerUrl;

                while (running) {

                    ReturnX result;
                    if (keepAlive) {
                        result = HttpXUtil.keepAlive(keepAliveUrl, param.getIp(), param.getPort());
                        if (!result.success()) {
                            logger.info("执行器保活失败，重新进行注册");
                            keepAlive = false;
                            continue;
                        }

                    } else {
                        result = HttpXUtil.register(registerUrl, param);
                        if (result.success()) {
                            try {
                                RegisterResult registerResult =
                                        JacksonUtil.toObject((String) result.getContent(), RegisterResult.class);

                                keepAliveUrl = keepAliveUrl.replace("register", registerResult.getKeepAliveUrl());
                                keepAlive = true;

                                logger.info("执行器注册成功，注册url: " + registerUrl);
                                logger.info("执行器与调度中心保活中...");
                            } catch (Exception e) {
                                logger.info("执行器注册时调度中心返回数据格式出错: " + e);
                            }

                        } else {
                            logger.info("执行器注册失败，注册url: " + registerUrl);
                        }
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
