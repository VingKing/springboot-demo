package com.example.plusdemo.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Project :
 * @Package Name : com.example.plusdemo.exception
 * @Description : 
 * @Author : zhangbin
 * @Create Date : 2019-11-07 14:06
 * @ModificationHistory Who   When     What
 * ------------    --------------    ---------------------------------
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AuthenticationFailException extends Exception {

}
