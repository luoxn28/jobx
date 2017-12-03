package com.luo.jobx.core.exception;

/**
 * 执行器异常类
 *
 * @author xiangnan
 */
public class JobException extends BaseException {

    public JobException(int code, String msg) {
        super(code, msg);
    }

    public JobException(JobException e) {
        super(e.getCode(), e.getMsg());
    }

}