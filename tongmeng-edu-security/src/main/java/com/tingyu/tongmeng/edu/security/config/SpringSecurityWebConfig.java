package com.tingyu.tongmeng.edu.security.config;

import com.tingyu.tongmeng.edu.security.filter.LoginAuthenticationFilter;
import com.tingyu.tongmeng.edu.security.filter.TokenAuthenticationFilter;
import com.tingyu.tongmeng.edu.security.utils.DefaultPasswordEncoder;
import com.tingyu.tongmeng.edu.security.utils.TokenLogoutHandler;
import com.tingyu.tongmeng.edu.security.utils.TokenManager;
import com.tingyu.tongmeng.edu.security.utils.UnauthorizedEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @Author essionshy
 * @Create 2020/11/2 19:32
 * @Version tongmeng-edu
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityWebConfig extends WebSecurityConfigurerAdapter {


    private UserDetailsService userDetailsService;

    private DefaultPasswordEncoder defaultPasswordEncoder;
    private TokenManager tokenManager;

    private RedisTemplate redisTemplate;

    @Autowired
    public SpringSecurityWebConfig(UserDetailsService userDetailsService,
                                   DefaultPasswordEncoder defaultPasswordEncoder,
                                   RedisTemplate redisTemplate, TokenManager tokenManager) {
        this.defaultPasswordEncoder = defaultPasswordEncoder;
        this.userDetailsService = userDetailsService;
        this.redisTemplate = redisTemplate;
        this.tokenManager = tokenManager;

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.exceptionHandling()
                .authenticationEntryPoint(new UnauthorizedEntryPoint())
                .and().csrf().disable()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and().logout().logoutUrl("/admin/sys/logout")
                .addLogoutHandler(new TokenLogoutHandler(tokenManager, redisTemplate)).and()
                .addFilter(new LoginAuthenticationFilter(authenticationManager(), tokenManager, redisTemplate))
                .addFilter(new TokenAuthenticationFilter(authenticationManager(), tokenManager, redisTemplate)).httpBasic();

    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(defaultPasswordEncoder);
    }

    /**
     * 配置哪些请求不被拦截
     *
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {

            web.ignoring().antMatchers("/api/**","/v2/**","swagger-resource/**","/webjars/**","swagger-ui.html/**");
        //web.ignoring().antMatchers("/*/**");
    }
}
