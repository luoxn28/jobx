package com.luo.jobx.core.rpc.service.impl;

import com.luo.jobx.core.bean.ExecutorInfo;
import com.luo.jobx.core.exception.ExceptionX;
import com.luo.jobx.core.exception.SystemException;
import com.luo.jobx.core.executor.ExecutorStarter;
import com.luo.jobx.core.rpc.service.ExecutorService;
import com.luo.jobx.core.util.R;
import com.xiaoleilu.hutool.convert.Convert;
import com.xiaoleilu.hutool.setting.dialect.Props;
import com.xiaoleilu.hutool.util.StrUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 执行器服务类，用于调用中心更新执行器信息
 *
 * @author xiangnan
 */
public class ExecutorServiceImpl implements ExecutorService {

    private final static Logger logger = LogManager.getLogger(ExecutorServiceImpl.class);

    @Override
    public boolean updateExecutorInfo(ExecutorInfo executorInfo) {

        // 参数安全性校验
        if (!StrUtil.equals(executorInfo.getToken(), ExecutorStarter.TOKEN) ||
                !StrUtil.equals(executorInfo.getIp(), ExecutorStarter.IP) ||
                (executorInfo.getPort() != ExecutorStarter.PORT)) {
            logger.info("已拦截更改执行器信息的非法请求!");
            return false;
        }

        // 经配置写入配置文件
        File file = new File(ExecutorStarter.CONFIG_FILE_PATH);
        if (!file.exists()) {
            // 配置文件不存在抛出异常
            logger.info("配置文件竟然不存在，呜呜呜...");
            return false;
        }

        try {
            FileWriter writer = new FileWriter(file);
            Props props = new Props();
            props.put(R.executorProperties.ip, ExecutorStarter.IP);
            props.put(R.executorProperties.port, Convert.toStr(ExecutorStarter.PORT));
            props.put(R.executorProperties.registerUrl, ExecutorStarter.REGISTER_URL);
            props.put(R.executorProperties.name, executorInfo.getName());
            props.put(R.executorProperties.keepAliveTime, Convert.toStr(executorInfo.getKeepAliveTime()));
            props.store(writer, "update by executor. :)");
            writer.close();
        } catch (IOException e) {
            return false;
        }

        ExecutorStarter.NAME = executorInfo.getName();
        ExecutorStarter.KEEP_ALIVE_TIME = executorInfo.getKeepAliveTime();

        // 打印执行器最新配置
        logger.info("调度中心更改了配置，最新配置如下:");
        ExecutorStarter.logExecutorConfigInfo();

        return true;
    }
}
