package com.example.plusdemo.interceptor;

import com.example.plusdemo.annotation.PassToken;
import com.example.plusdemo.exception.AuthenticationFailException;
import com.example.plusdemo.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @Project : manager_backend
 * @Package Name : com.sunlands.feo.interceptor
 * @Description : Token拦截器
 * @Author : zhangbin
 * @Create Date : 2019年03月17日 15:42
 * @ModificationHistory Who   When     What
 * ------------    --------------    ---------------------------------
 */
@Slf4j
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws AuthenticationFailException {
        // 如果不是映射到方法直接通过
        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod=(HandlerMethod)handler;
        Method method=handlerMethod.getMethod();
        Class<?> clazz = handlerMethod.getBeanType();
        if (clazz == BasicErrorController.class){
            return true;
        }
        if (clazz.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = clazz.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
        //检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
        String token = request.getHeader(tokenHeader);
        // 执行认证
        if (StringUtils.isBlank(token)) {
            throw new AuthenticationFailException();
        }
        // 验证 token
        boolean flag = TokenUtil.verifyToken(token);
        if(!flag){
            throw new AuthenticationFailException();
        }
        return true;
    }
}
