package com.luo.jobx.core.rpc.encoder;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * RCP请求bean
 *
 * @author xiangnan
 */
@Data
@NoArgsConstructor
public class RpcRequest implements Serializable {
    private String toUrl;
    private String clazz;          // RPC调用类
    private String method;         // RPC调用类方法
    private Class<?>[] paramType;  // 参数类型
    private Object[] param;        // 参数
}
