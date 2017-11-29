package com.luo.jobx.core.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 执行器注册时调度中心的返回类
 *
 * @author xiangnan
 */
@Data
@NoArgsConstructor
public class RegisterResult implements Serializable {
    private String keepAliveUrl;
}
