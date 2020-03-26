package com.example.plusdemo.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author VingKing
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AuthenticationFailException extends Exception {

}
