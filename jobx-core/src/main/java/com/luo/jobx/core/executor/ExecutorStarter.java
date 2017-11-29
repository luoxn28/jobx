package com.luo.jobx.core.executor;

import com.luo.jobx.core.component.ArgsComponent;
import com.luo.jobx.core.exception.ExceptionX;
import com.luo.jobx.core.exception.SystemException;
import com.luo.jobx.core.rpc.jetty.JettyServer;
import com.luo.jobx.core.util.R;
import com.luo.jobx.core.util.SystemUtil;
import com.xiaoleilu.hutool.convert.Convert;
import com.xiaoleilu.hutool.setting.dialect.Props;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileWriter;

/**
 * 执行器启动器
 *
 * @author xiangnan
 */
@Component
public class ExecutorStarter {

    private final static Logger logger = LogManager.getLogger(ExecutorStarter.class);

    // 执行器配置文件，所在目录为当前程序运行目录
    private static final String CONFIG_FILE_NAME = "executor.properties";
    private static final String CONFIG_FILE_PATH = SystemUtil.applicationPath + CONFIG_FILE_NAME;

    @Resource
    private ArgsComponent argsComponent;

    @Resource
    private JettyServer jettyServer;

    public void run(String... args) throws Exception {

        String ip, registerUrl;
        int port;

        if (args.length >= 3) {
            // 使用输入参数来启动executor
            argsComponent.check(args);

            ip = args[0];
            port = Convert.toInt(args[1]);
            registerUrl = args[2];

            // ip/port/registerUrl存储到 CONFIG_FILE_NAME 文件中
            FileWriter writer = new FileWriter(CONFIG_FILE_PATH);
            Props props = new Props();
            props.put(R.executorProperties.ip, ip);
            props.put(R.executorProperties.port, Convert.toStr(port));
            props.put(R.executorProperties.registerUrl, registerUrl);
            props.store(writer, "first created by executor. :)");
            writer.close();
        } else {
            File file = new File(CONFIG_FILE_PATH);
            if (!file.exists()) {
                // 配置文件不存在抛出异常
                throw new SystemException(ExceptionX.START_FAIL);
            }

            Props props = new Props(file);
            ip = Convert.toStr(props.get(R.executorProperties.ip));
            port = Convert.toInt(props.get(R.executorProperties.port));
            registerUrl = Convert.toStr(props.get(R.executorProperties.registerUrl));
        }

        logger.info("执行器配置文件: " + CONFIG_FILE_PATH);
        logger.info("执行器ip: " + ip);
        logger.info("执行器port: " + String.valueOf(port));
        logger.info("注册地址: " + registerUrl);

        jettyServer.start(ip, port, registerUrl).join();
    }

}