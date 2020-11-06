package com.tingyu.tongmeng.edu.security.utils;

import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author essionshy
 * @Create 2020/11/2 19:51
 * @Version tongmeng-edu
 */
@Component
public class TokenManager {
    private static final long TOKEN_EXPIRATION=1000*60*30;//token过期时间
    private static final String KEY_SECRET="xZ299kkw";//根据一定规则生成的秘钥 任意

    public  String createToken(String username){
        String token = Jwts.builder().setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION))
                .signWith(SignatureAlgorithm.HS512, KEY_SECRET).compressWith(CompressionCodecs.GZIP).compact();
        return token;
    }

    /**
     * 从token中获取登录用户名
     * @param token
     * @return
     */
    public String getUserFromToken(String token) {
        String username = Jwts.parser().setSigningKey(KEY_SECRET).parseClaimsJws(token).getBody().getSubject();
        return username;
    }
}
