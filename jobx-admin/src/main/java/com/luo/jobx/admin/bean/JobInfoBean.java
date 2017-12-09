package com.luo.jobx.admin.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * JobInfoBean
 *
 * @author xiangnan
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobInfoBean {
    private String jobId;
    private String jobName;
    private String jobType;
    private String jobRole;
    private String cron;
    private String desc;
    private String param;
    private String className;
    private String scriptPath;
    private String classNameScriptPath;
    private String paramCreator;
    private String paramDynamic;
    private String emailPhone;
    private String routeStrategy;
    private String failStrategy;
    private String createTime;
    private String updateTime;
    private String status;
}
