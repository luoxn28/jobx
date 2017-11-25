package com.luo.jobx.core.component;

import com.luo.jobx.core.exception.ExceptionX;
import com.luo.jobx.core.exception.SystemException;
import com.xiaoleilu.hutool.util.StrUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * 参数检查类
 */
@Component
public class ArgsComponent {

    private final Logger logger = LogManager.getLogger(ArgsComponent.class);

    public boolean check(String... args) {
        if (args.length < 3) {
            throw new SystemException(ExceptionX.START_FAIL);
        }

        return check(args[0], args[1], args[2]);
    }

    private boolean check(String ip, String port, String registerUrl) {

        try {
            logger.info("执行器ip: " + ip);
            logger.info("执行器port: " + String.valueOf(port));
            logger.info("注册地址: " + registerUrl);

            if (StrUtil.isBlank(ip) || !ip.contains(".")) {
                throw ExceptionX.START_FAIL;
            }

            if (StrUtil.isBlank(registerUrl) || !StrUtil.startWith(registerUrl, "http://")) {
                throw ExceptionX.START_FAIL;
            }

            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new SystemException(ExceptionX.START_FAIL);
        }

    }

}
