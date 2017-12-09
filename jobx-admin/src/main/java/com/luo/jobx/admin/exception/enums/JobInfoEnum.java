package com.luo.jobx.admin.exception.enums;

import lombok.AllArgsConstructor;

/**
 * 任务异常信息枚举
 */
@AllArgsConstructor
public enum JobInfoEnum {
    UNKNOWN_ERROR(-1, "UNKNOWN_ERROR", "未知错误"),

    PARAM_ERROR(-1, "PARAM_ERROR", "参数错误"),
    JOB_EXIST(-1, "JOB_EXIST", "任务已存在"),

    CRON_ERROR(-1, "CRON_ERROR", "cron表达式错误");

    private int code;
    private String codeMsg;
    private String msg;

    public int getCode() {
        return code;
    }

    public String codeMsg() {
        return codeMsg;
    }

    public String getMsg() {
        return msg;
    }
}
