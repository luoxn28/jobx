package com.luo.jobx.core.rpc.encoder;

import com.luo.jobx.core.util.R;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * RPC调用返回类
 *
 * @author xiangnan
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RpcResponse implements Serializable {
    private String status;
    private Object result;

    public boolean isSuccess() {
        return (status == null) || (status.equals(R.status.SUCCESS));
    }
}
