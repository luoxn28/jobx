package com.luo.jobx.admin.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * ExecutorInfoBean
 *
 * @author xiangnan
 */
@Data
@NoArgsConstructor
public class ExecutorInfoBean {
    private String executorId;
    private String ip;
    private int port;
    private String name;
    private String status;         // 执行器当前状态，见 R.executorStatus
    private int keepAliveTime;     // 与调度器的保活时间（单位s）
    private Date registerTime;
}
