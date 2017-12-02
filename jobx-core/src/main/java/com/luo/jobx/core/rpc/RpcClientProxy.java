package com.luo.jobx.core.rpc;

import com.luo.jobx.core.exception.ExceptionX;
import com.luo.jobx.core.rpc.encoder.RpcRequest;
import com.luo.jobx.core.rpc.encoder.RpcResponse;
import com.luo.jobx.core.util.RpcUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * RPC代理
 *
 * @author xiangnan
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RpcClientProxy implements FactoryBean<Object> {

    private String toUrl;
    private Class<?> clazz;

    public RpcClientProxy(Class<?> clazz, String toUrl) {
        this.clazz = clazz;
        this.toUrl = toUrl;
    }

    @Override
    public Object getObject() throws Exception {

        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[] { clazz },
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                        RpcRequest request = new RpcRequest();
                        request.setToUrl(toUrl);
                        request.setClazz(method.getDeclaringClass().getName());
                        request.setMethod(method.getName());
                        request.setParamType(method.getParameterTypes());
                        request.setParam(args);

                        RpcResponse response = RpcUtil.send(request);
                        if (response.isSuccess()) {
                            return response.getResult();
                        } else {
                            throw ExceptionX.RPC_FAIL;
                        }
                    }
                });

    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

}
