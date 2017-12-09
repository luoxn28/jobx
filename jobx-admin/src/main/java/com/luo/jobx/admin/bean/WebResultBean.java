package com.luo.jobx.admin.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * web返回结果封装类
 *
 * @author xiangnan
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WebResultBean <T> {
    private int code;     // 状态码
    private String msg;   // 提示信息
    private T data;       // 数据
}
