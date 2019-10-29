package com.example.plusdemo.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Package Name : com.sunlands.feo.utils
 * @Description : Token相关工具类
 * @Author : ZhangBin
 * @Create Date : 2019/3/18
 * @ModificationHistory Who   When     What
 */
@Component
@Slf4j
public class TokenUtil {
    /**
     * 加密密钥
     */

    private static String SECRET;
    /**
     * 过期时间
     */
    private static String EXPIRE_TIME;

    private static String JWT_ISSUER;

    private static String USERNAME_CLAIM;

    @Value("${jwt.secret}")
    public void setSecret(String secret) {
        TokenUtil.SECRET = secret;
    }

    @Value("${jwt.expiration}")
    public void setExpireTime(String expireTime) {
        TokenUtil.EXPIRE_TIME = expireTime;
    }

    @Value("${jwt.issuer}")
    public void setJwtIssuer(String jwtIssuer) {
        TokenUtil.JWT_ISSUER = jwtIssuer;
    }

    @Value("${jwt.claim}")
    public void setUsernameClaim(String usernameClaim) {
        TokenUtil.USERNAME_CLAIM = usernameClaim;
    }

    /**
     * 生成token
     *
     * @param username 用户263
     * @return java.lang.String
     */
    public static String generateToken(String username) {
        try {
            Map<String, Object> headerClaims = new HashMap<>(2);
            headerClaims.put("alg", "HS256");
            headerClaims.put("typ", "JWT");
            long currentTimeMillis = System.currentTimeMillis();
            Date expireDate = new Date(currentTimeMillis + Long.parseLong(EXPIRE_TIME) * 1000);
            // 附带username信息
            return JWT.create()
                    .withHeader(headerClaims)
                    .withIssuer(JWT_ISSUER)
                    .withClaim(USERNAME_CLAIM, username)
                    .withIssuedAt(new Date(currentTimeMillis))
                    .withExpiresAt(expireDate)
                    .sign(Algorithm.HMAC256(SECRET));
        } catch (Exception exception) {
            log.error("生成token异常", exception);
            return null;
        }
    }

    /**
     * 验证token
     *
     * @param token token值
     * @return boolean
     */
    public static boolean verifyToken(String token) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET))
                    .withIssuer(JWT_ISSUER)
                    .build();
            verifier.verify(token);
            return true;
        } catch (Exception e) {
            log.error("验证token异常", e);
            return false;
        }
    }

    /**
     * 校验token
     *
     * @param token    token值
     * @param username 用户名
     * @return boolean
     */
    public static boolean verify(String token, String username) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET))
                    .withIssuer(JWT_ISSUER)
                    .withClaim(USERNAME_CLAIM, username)
                    .build();
            verifier.verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 获取用户名
     *
     * @param token token值
     * @return java.lang.Long
     */
    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim(USERNAME_CLAIM).asString();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取颁发时间
     *
     * @param token token值
     * @return java.util.Date
     */
    public static Date getIssuedDate(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getIssuedAt();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取过期时间
     *
     * @param token token值
     * @return java.util.Date
     */
    public static Date getExpireDate(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getExpiresAt();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 判断是否过期
     *
     * @param token token值
     * @return boolean
     */
    public static boolean isExpire(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getExpiresAt().compareTo(new Date()) <= 0;
        } catch (Exception e) {
            return true;
        }
    }
}