package com.luo.jobx.core.util;

/**
 * jobx资源类
 *
 * @author xiangnan
 */
public interface R {

    interface status {
        String SUCCESS = "SUCCESS";
        String FAIL = "FAIL";
    }

    interface executorStatus {
        String ONLINE = "ONLINE";
        String OFFLINE = "OFFLINE";
        String DELETED = "DELETED";
    }

    /**
     * 执行器配置项
     */
    interface executorProperties {
        String ip = "ip";
        String port = "port";
        String name = "name";
        String registerUrl = "registerUrl";
        String keepAliveTime = "keepAliveTime";
    }

}
