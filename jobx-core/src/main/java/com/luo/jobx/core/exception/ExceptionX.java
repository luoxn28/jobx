package com.luo.jobx.core.exception;

/**
 * 异常容器类
 *
 * @author xiangnan
 */
public interface ExceptionX {

    SystemException START_FAIL = new SystemException(-1, "执行器启动参数错误");
    SystemException RPC_FAIL = new SystemException(-2, "RPC通信错误");
    SystemException NO_RPC_SERVICE = new SystemException(-3, "未找到RCP服务");

}
