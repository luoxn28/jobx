package com.luo.jobx.core.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 执行器更新类，用于调度中心在线更改执行器信息
 *
 * @author xiangnan
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExecutorInfo implements Serializable {
    private String ip;
    private int port;
    private String token;
    private String name;
    private int keepAliveTime;
}
