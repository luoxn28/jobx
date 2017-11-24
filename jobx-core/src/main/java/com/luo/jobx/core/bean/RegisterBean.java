package com.luo.jobx.core.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 执行器注册类
 *
 * @author xiangnan
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterBean {
    private String ip;
    private int port;
    private String name; // 执行器名字
}
