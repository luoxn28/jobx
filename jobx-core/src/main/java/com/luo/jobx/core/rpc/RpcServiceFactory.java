package com.luo.jobx.core.rpc;

import com.luo.jobx.core.exception.ExceptionX;
import com.luo.jobx.core.rpc.encoder.RpcRequest;
import com.luo.jobx.core.rpc.encoder.RpcResponse;
import com.luo.jobx.core.rpc.service.TriggerService;
import com.luo.jobx.core.rpc.service.impl.TriggerServiceImpl;
import com.luo.jobx.core.util.R;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.cglib.reflect.FastClass;
import org.springframework.cglib.reflect.FastMethod;

import java.util.concurrent.ConcurrentHashMap;

/**
 * RPC服务工厂类
 *
 * @author xiangnan
 */
public class RpcServiceFactory {

    private final static Logger logger = LogManager.getLogger(RpcServiceFactory.class);

    private static ConcurrentHashMap<String, Class> serviceMap = new ConcurrentHashMap<>();

    // 服务初始化
    static {
        registerService(TriggerService.class, TriggerServiceImpl.class);
    }

    public static void registerService(Class clazz) {
        serviceMap.put(clazz.getName(), clazz);
    }

    public static void registerService(Class clazz, Class clazzImpl) {
        serviceMap.put(clazz.getName(), clazzImpl);
    }

    public static RpcResponse invokeService(RpcRequest request) {
        Class clazz = serviceMap.get(request.getClazz());
        if (clazz == null) {
            return new RpcResponse(R.status.FAIL, ExceptionX.NO_RPC_SERVICE.msg);
        }

        RpcResponse response = new RpcResponse();

        FastClass serviceClass = FastClass.create(clazz);
        FastMethod serviceMethod = serviceClass.getMethod(request.getMethod(), request.getParamType());

        try {
            Object result = serviceMethod.invoke(clazz.newInstance(), request.getParam());
            response.setResult(result);
        } catch (Exception e) {
            logger.error("创建服务类失败: " + clazz);
            response.setResult(ExceptionX.NO_RPC_SERVICE.msg);
            e.printStackTrace();
        }

        return response;
    }

}
