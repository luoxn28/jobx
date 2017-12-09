package com.luo.jobx.admin.aspect;

import com.luo.jobx.admin.util.WebResultUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * web请求aop注解，封装返回结果
 *
 * @author xiangnan
 */
@Aspect
@Component
@Order(1)
public class WebResultAspect {

    @Around("execution(public * com.luo.jobx.admin.controller.ExecutorInfoController.*(..)) || " +
            "execution(public * com.luo.jobx.admin.controller.JobInfoController.*(..))")
    public Object aroundMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            Object result = joinPoint.proceed();
            return WebResultUtil.success(result);
        } catch (Exception e) {
            // 返回异常信息
            return WebResultUtil.error(-1, e.getMessage());
        }
    }

}
