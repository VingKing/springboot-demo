package com.example.plusdemo.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Project : lion_backend_api
 * @Package Name : com.sunlands.feo.exception
 * @Description :
 * @Author : pjz
 * @Create Date : 2019年03月19日 下午 03:14
 * @ModificationHistory Who   When     What
 * ------------    --------------    ---------------------------------
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AuthenticationFailException extends Exception {

}
