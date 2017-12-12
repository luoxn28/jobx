package com.luo.jobx.core.component;

import com.luo.jobx.core.exception.ExceptionX;
import com.luo.jobx.core.exception.SystemException;
import com.xiaoleilu.hutool.util.StrUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * 参数检查类
 */
@Component
public class ArgsComponent {

    private static final Logger logger = LogManager.getLogger();

    public boolean check(String... args) {
        if (args.length < 3) {
            throw new SystemException(ExceptionX.START_FAIL);
        }

        return check(args[0], args[1], args[2]);
    }

    private boolean check(String ip, String port, String registerUrl) {

        try {
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
