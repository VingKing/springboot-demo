package com.example.plusdemo.controller;

import com.example.plusdemo.annotation.PassToken;
import com.example.plusdemo.util.TokenUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author VingKing
 */
@RestController
@RequestMapping("/open")
@PassToken
public class OpenController {

    /**
     * 模拟生成token
     * @param username
     * @return
     */
    @GetMapping("/token")
    public ResponseEntity getToken(String username) {
        String token = TokenUtil.generateToken(username);
        return ResponseEntity.ok(token);
    }
}
