package com.example.plusdemo.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @Project :
 * @Package Name : com.example.plusdemo.exception
 * @Description : 
 * @Author : zhangbin
 * @Create Date : 2019-11-07 14:07
 * @ModificationHistory Who   When     What
 * ------------    --------------    ---------------------------------
 */
@Slf4j
@ControllerAdvice
public class ExceptionHandle {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseEntity handle(HttpServletRequest httpServletRequest, Exception e) {
        if (e instanceof AuthenticationFailException) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(HttpStatus.FORBIDDEN);
        } else {
            log.error("系统异常,请求地址为:{}", httpServletRequest.getRequestURI(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}


