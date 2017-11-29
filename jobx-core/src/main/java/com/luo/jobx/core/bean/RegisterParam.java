package com.luo.jobx.core.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 执行器注册类
 *
 * @author xiangnan
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterParam implements Serializable {

    /**
     * 执行器每次启动产生一个token值，并发送给调度中心，
     * 之后调用中心与执行器通信时携带该token，用于验证。
     */
    private String token;

    private String ip;
    private int port;
    private String name; // 执行器名字

    public RegisterParam(String ip, int port, String token) {
        this.ip = ip;
        this.port = port;
        this.token = token;
    }
}
