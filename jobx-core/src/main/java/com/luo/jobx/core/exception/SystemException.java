package com.luo.jobx.core.exception;

/**
 * 执行器异常类
 *
 * @author xiangnan
 */
public class SystemException extends BaseException {

    public SystemException(int code, String msg) {
        super(code, msg);
    }

    public SystemException(SystemException e) {
        super(e.getCode(), e.getMsg());
    }

}
