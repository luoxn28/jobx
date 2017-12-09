package com.luo.jobx.admin.util;

import com.luo.jobx.admin.bean.WebResultBean;

/**
 * web返回信息辅助类
 *
 * @author xiangnan
 */
public class WebResultUtil {

    public static WebResultBean<Object> success(Object data) {
        return new WebResultBean<>(0, "success", data);
    }

    public static WebResultBean<Object> error(int code, Object data) {
        return new WebResultBean<>(code, "error", data);
    }

}
