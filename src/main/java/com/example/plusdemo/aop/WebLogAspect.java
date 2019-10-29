package com.example.plusdemo.aop;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Objects;

/**
 * @Package Name : com.sunlands.feo.aop
 * @Description : Aop切面类,切向所有控制层,输出接收和响应信息
 * @Author : ZhangBin
 * @Create Date : 2018/11/1
 * @ModificationHistory Who   When     What
 */
@Aspect
@Component
@Slf4j
public class WebLogAspect {

    @Pointcut("execution(* com.example.plusdemo.controller..*.*(..))")
    public void webLog() {
    }

    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();
        String requestURI = request.getRequestURI();
        // 记录下请求内容
        log.debug("REQUEST INFO:URL:{},ARGS:{}", requestURI, Arrays.toString(joinPoint.getArgs()));
        Object result = joinPoint.proceed();
        log.debug("RESPONSE INFO:{}", JSON.toJSONString(result));
        log.debug("SPEND TIME :{}ms", (System.currentTimeMillis() - startTime));
        return result;
    }
}