package com.luo.jobx.core.rpc.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 任务触发调用参数
 *
 * @author xiangnan
 */
@Data
@NoArgsConstructor
public class TriggerParam implements Serializable {
    private String jobId;
    private String logId;
}
