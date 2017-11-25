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
    private String ip;
    private int port;
    private String name; // 执行器名字

    public RegisterParam(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }
}
