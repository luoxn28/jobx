package com.luo.jobx.admin.exception;

import com.luo.jobx.admin.exception.enums.JobInfoEnum;
import com.luo.jobx.core.exception.BaseException;

/**
 * 任务信息异常类
 *
 * @author xiangnan
 */
public class JobInfoException extends BaseException {

    public JobInfoException(JobInfoEnum en) {
        super(en.getCode(), en.getMsg());
    }

    public JobInfoException(JobInfoEnum en, String msg) {
        super(en.getCode(), msg);
    }

}
