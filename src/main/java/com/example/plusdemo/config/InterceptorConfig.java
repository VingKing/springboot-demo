package com.example.plusdemo.config;

import com.example.plusdemo.interceptor.AuthenticationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Project :
 * @Package Name : com.example.plusdemo.config
 * @Description : 
 * @Author : zhangbin
 * @Create Date : 2019-11-07 14:07
 * @ModificationHistory Who   When     What
 * ------------    --------------    ---------------------------------
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor())
                .addPathPatterns("/**");
    }

    @Bean
    public AuthenticationInterceptor authenticationInterceptor() {
        return new AuthenticationInterceptor();
    }
}