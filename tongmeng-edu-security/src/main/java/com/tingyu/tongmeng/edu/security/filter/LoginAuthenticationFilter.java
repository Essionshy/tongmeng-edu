package com.tingyu.tongmeng.edu.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tingyu.tongmeng.edu.commons.R;
import com.tingyu.tongmeng.edu.security.entity.SecurityUser;
import com.tingyu.tongmeng.edu.security.entity.LoginUser;
import com.tingyu.tongmeng.edu.security.utils.ResponseUtil;
import com.tingyu.tongmeng.edu.security.utils.TokenManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 自定义类继承UsernamePasswordAuthenticationFilter 并重写下面三个方法
 * 通过构造注入容器，不采用自动装配
 * @Author essionshy
 * @Create 2020/11/2 19:34
 * @Version tongmeng-edu
 */
public class LoginAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;
    private TokenManager tokenManager;
    private RedisTemplate redisTemplate;


    public LoginAuthenticationFilter(AuthenticationManager authenticationManager, TokenManager tokenManager, RedisTemplate redisTemplate) {
        this.authenticationManager = authenticationManager;
        this.tokenManager = tokenManager;
        this.redisTemplate = redisTemplate;
        this.setPostOnly(false);
        this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/admin/sys/login","POST"));
    }


    /**
     * 尝试认证
     *
     * @param request
     * @param response
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

            try {
                LoginUser loginUser = new ObjectMapper().readValue(request.getInputStream(), LoginUser.class);

                return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword(), new ArrayList<>()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }


    /**
     * 登录认证成功
     *
     * @param request
     * @param response
     * @param chain
     * @param authResult
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        //认证成功后，创建根据用户名创建token返回给客户端

        SecurityUser user = (SecurityUser) authResult.getPrincipal();

        String token = tokenManager.createToken(user.getCurrentLoginUser().getUsername());
        redisTemplate.opsForValue().set(user.getCurrentLoginUser().getUsername(), user.getPermissionValueList());

        ResponseUtil.out(response, R.ok().data("token", token));


    }


    /**
     * 登录认证失败
     *
     * @param request
     * @param response
     * @param failed
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        ResponseUtil.out(response, R.error());
    }
}
