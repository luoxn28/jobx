package com.luo.jobx.core.executor;

import com.luo.jobx.core.bean.RegisterParam;
import com.luo.jobx.core.component.ArgsComponent;
import com.luo.jobx.core.exception.ExceptionX;
import com.luo.jobx.core.exception.SystemException;
import com.luo.jobx.core.rpc.jetty.JettyServer;
import com.luo.jobx.core.thread.RegisterThread;
import com.luo.jobx.core.util.BusinessIDGenerator;
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

    // 执行器配置文件，所在目录为当前程序运行目录，注意配置文件中内容可能为中文，注意编码格式问题
    private static final String CONFIG_FILE_NAME = "executor.properties";
    public static final String CONFIG_FILE_PATH = SystemUtil.applicationPath + CONFIG_FILE_NAME;

    public static final String TOKEN = BusinessIDGenerator.getId();
    public static String REGISTER_URL = "";
    public static String IP = "";
    public static int PORT = 9999;
    public static String NAME = "";
    public static int KEEP_ALIVE_TIME = 10;

    @Resource
    private ArgsComponent argsComponent;

    @Resource
    private RegisterThread registerThread;

    @Resource
    private JettyServer jettyServer;

    public static void logExecutorConfigInfo() {
        logger.info("执行器配置文件: " + CONFIG_FILE_PATH);
        logger.info("注册地址: " + REGISTER_URL);
        logger.info("执行器ip: " + IP);
        logger.info("执行器port: " + PORT);
        logger.info("执行器名字: " + NAME);
        logger.info("执行器心跳间隔(s): " + KEEP_ALIVE_TIME);
    }

    public void run(String... args) throws Exception {

        String ip, registerUrl;
        String name = null;
        int port, keepAliveTime = 0;

        if (args.length == 3) {
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
        } else if (args.length == 0) {
            File file = new File(CONFIG_FILE_PATH);
            if (!file.exists()) {
                // 配置文件不存在抛出异常
                throw new SystemException(ExceptionX.START_FAIL);
            }

            Props props = new Props(file);
            if ((props.get(R.executorProperties.ip) == null) ||
                    (props.get(R.executorProperties.port) == null) ||
                    (props.get(R.executorProperties.registerUrl)  == null)) {
                logger.error("配置文件参数错误");
                throw new SystemException(ExceptionX.START_FAIL);
            }

            ip = Convert.toStr(props.get(R.executorProperties.ip));
            port = Convert.toInt(props.get(R.executorProperties.port));
            registerUrl = Convert.toStr(props.get(R.executorProperties.registerUrl));

            if (props.get(R.executorProperties.keepAliveTime) != null) {
                keepAliveTime = Convert.toInt(props.get(R.executorProperties.keepAliveTime));
            }
            if (keepAliveTime < 10) {
                keepAliveTime = 10;
            }

            if (props.get(R.executorProperties.name) != null) {
                name = Convert.toStr(props.get(R.executorProperties.name));
            } else {
                // 执行器默认名字
                name = "executor";
            }
        } else {
            throw new SystemException(ExceptionX.START_FAIL);
        }

        // 保存ip:port配置
        REGISTER_URL = registerUrl;
        IP = ip;
        PORT = port;
        NAME = name;
        KEEP_ALIVE_TIME = keepAliveTime;

        logger.info("执行器TOKEN: " + TOKEN);
        logExecutorConfigInfo();

        try {

            RegisterParam registerParam = new RegisterParam(ip, port, TOKEN);
            registerParam.setKeepAliveTime(keepAliveTime);
            registerParam.setName(name);

            registerThread.start(registerUrl, registerParam);
            jettyServer.start(ip, port);

            // 主线程join等待
            jettyServer.join();
        } catch (Exception e) {
            logger.warn("执行器运行异常: " + e);
        } finally {
            destroy();
        }
    }

    private void destroy() {
        if (jettyServer != null) {
            jettyServer.destroy();
        }

        if (registerThread != null) {
            registerThread.stop();
        }
    }

}