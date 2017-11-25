package com.luo.jobx.core.exception;

/**
 * 异常容器类
 *
 * @author xiangnan
 */
public interface ExceptionX {

    SystemException START_FAIL = new SystemException(-1, "执行器启动参数错误");

}
