package com.tingyu.tongmeng.edu.commons;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @Author essionshy
 * @Create 2020/10/30 9:41
 * @Version tongmeng-edu
 */
public class JwtUtils {

    private static final long TOKEN_EXPIRATION=1000*60*30;//token过期时间
    private static final String KEY_SECRET="xZ299kkw";//根据一定规则生成的秘钥 任意

    private JwtUtils(){}

    /**
     * 根据用户ID和用户昵称生成Token
     * @param id
     * @param nickname
     * @return
     */
    public static String getToken(String id,String nickname){

        String token= Jwts.builder()
                .setHeaderParam("typ","JWT")
                .setHeaderParam("alg","HS256")
                .setSubject("tongmeng-user")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+TOKEN_EXPIRATION))

                .claim("id",id)
                .claim("nickname",nickname)

                .signWith(SignatureAlgorithm.HS256,KEY_SECRET) //指定签名生成方式
                .compact();
        return token;
    }


    /**
     * 判断 token是否存在与有效
     * @param token
     * @return
     */
    public static boolean validateToken(String token){

        if(StringUtils.isEmpty(token))
            return false;
        try{
            Jwts.parser().setSigningKey(KEY_SECRET).parseClaimsJws(token);


        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean validateToken(HttpServletRequest request) {
        try{
            //验证token是否存在
            String token = request.getHeader("token");
            if(StringUtils.isEmpty(token)){
                return false;
            }
            //验证token是否有效
            Jwts.parser().setSigningKey(KEY_SECRET).parseClaimsJws(token);


        }catch (Exception e){
         e.printStackTrace();
         return false;
        }
        return true;
    }

    /**
     * 根据请求返回用户ID
     * @param request
     * @return
     */
    public static String getUserIdByToken(HttpServletRequest request){
        String result="";

        String token = request.getHeader("token");
        if(StringUtils.isEmpty(token)){
            //token不存在则抛
            throw new ResultException(28004,"用户未登录");

        }else if (!validateToken(token)){
            throw new ResultException(28004,"token 已过期");
        }else{
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(KEY_SECRET).parseClaimsJws(token);
            Claims claims = claimsJws.getBody();
            result= (String) claims.get("id");
        }



        return result;

    }
}
