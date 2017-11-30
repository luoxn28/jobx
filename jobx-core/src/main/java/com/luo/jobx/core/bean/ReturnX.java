package com.luo.jobx.core.bean;

import com.luo.jobx.core.util.R;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 通用返回值类
 *
 * @author xiangnan
 */
@Data
@NoArgsConstructor
public class ReturnX<T> implements Serializable {
    private String status;
    private T content;

    public static ReturnX<String> SUCCESS = new ReturnX<>(R.status.SUCCESS, null);
    public static ReturnX<String> FAIL = new ReturnX<>(R.status.FAIL, null);

    public ReturnX(String status, T content) {
        this.status = status;
        this.content = content;
    }

    public boolean success() {
        return status.equals(R.status.SUCCESS);
    }

}
