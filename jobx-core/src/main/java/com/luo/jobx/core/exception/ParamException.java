package com.luo.jobx.core.exception;

/**
 * 执行器异常类
 *
 * @author xiangnan
 */
public class ParamException extends BaseException {

    public ParamException(int code, String msg) {
        super(code, msg);
    }

    public ParamException(ParamException e) {
        super(e.getCode(), e.getMsg());
    }

}
