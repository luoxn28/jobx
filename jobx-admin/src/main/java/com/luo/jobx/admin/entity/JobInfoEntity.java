package com.luo.jobx.admin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 任务信息Entity
 *
 * @author xiangnan
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobInfoEntity {
    private int id;
    private String jobId;
    private String jobName;
    private String jobType;
    private String jobRole;
    private String cron;
    private String desc;
    private String param;
    private String scriptPath;
    private String className;
    private String paramCreator;
    private String paramDynamic;
    private String emailPhone;
    private String routeStrategy;
    private String failStrategy;
    private Date createTime;
    private Date updateTime;
    private String status;

    public JobInfoEntity(String jobId) {
        this.jobId = jobId;
    }
}
