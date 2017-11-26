package com.luo.jobx.core.rpc.jetty;

import com.luo.jobx.core.exception.ExceptionX;
import com.luo.jobx.core.rpc.RpcServiceFactory;
import com.luo.jobx.core.rpc.encoder.RpcRequest;
import com.luo.jobx.core.rpc.encoder.RpcResponse;
import com.luo.jobx.core.util.HessianUtil;
import com.luo.jobx.core.util.HttpXUtil;
import com.luo.jobx.core.util.R;
import com.xiaoleilu.hutool.util.ArrayUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Jetty服务处理类，处理RPC请求
 *
 * @author xiangnan
 */
public class JettyServerHandler extends AbstractHandler {

    private final static Logger logger = LogManager.getLogger(JettyServerHandler.class);

    @Override
    public void handle(String s, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        // RCP服务调用
        byte[] responseBytes = HessianUtil.serialize(invoke(request));

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        baseRequest.setHandled(true);

        OutputStream out = response.getOutputStream();
        out.write(responseBytes);
        out.flush();
    }

    private RpcResponse invoke(HttpServletRequest request) {
        try {
            byte[] data = HttpXUtil.readByte(request);
            if (ArrayUtil.isEmpty(data)) {
                return new RpcResponse(R.status.FAIL, "RpcRequest byte[] is null");
            }

            RpcRequest rpcRequest = HessianUtil.deserialize(data);
            return RpcServiceFactory.invokeService(rpcRequest);
        } catch (IOException e) {
            logger.error("RPC调用错误: " + e);
            return new RpcResponse(R.status.FAIL, ExceptionX.RPC_FAIL.msg);
        }
    }

}
