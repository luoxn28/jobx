package com.luo.jobx.executor.exception;

import lombok.Data;

/**
 * 异常类
 *
 * @author xiangnan
 */
@Data
public class BaseException extends RuntimeException {
    private int code;
    private String msg;

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
