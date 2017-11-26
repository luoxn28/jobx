package com.luo.jobx.core.util;

import com.luo.jobx.core.rpc.encoder.RpcRequest;
import com.luo.jobx.core.rpc.encoder.RpcResponse;
import com.xiaoleilu.hutool.util.ArrayUtil;

import java.io.IOException;

/**
 * Rpc辅助类
 *
 * @author xiangnan
 */
public class RpcUtil {

    public static RpcResponse send(RpcRequest request) throws IOException {
        RpcResponse rpcResponse;
        byte[] response;

        response = HttpXUtil.postRpc(request.getToUrl(), HessianUtil.serialize(request));
        if (ArrayUtil.isEmpty(response)) {
            rpcResponse = new RpcResponse(R.status.FAIL, "RpcResponse byte[] is null");
        } else {
            rpcResponse = HessianUtil.deserialize(response);
        }

        return rpcResponse;
    }

}
