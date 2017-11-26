package com.luo.jobx.core.exception;

import lombok.Data;

/**
 * 异常类
 *
 * @author xiangnan
 */
@Data
public class BaseException extends RuntimeException {
    public int code;
    public String msg;

    public BaseException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public BaseException(int code, String msg, Throwable cause) {
        super(msg, cause);
        this.code = code;
    }

}
