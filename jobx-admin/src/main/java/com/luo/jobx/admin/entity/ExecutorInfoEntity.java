package com.luo.jobx.admin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 执行器信息Entity
 *
 * @author xiangnan
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExecutorInfoEntity {
    private int id;
    private String executorId;
    private String ip;
    private int port;
    private String name;
    private String token;
    private String status;         // 执行器当前状态，见 R.executorStatus
    private int keepAliveTime;     // 与调度器的保活时间（单位s）
    private Date registerTime;
    private Date updateTime;
}
