package com.tingyu.tongmeng.edu.service.edu.controller;

import com.tingyu.tongmeng.edu.commons.R;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * @Author essionshy
 * @Create 2020/10/26 10:22
 * @Version tongmeng-edu
 */
@RestController
@RequestMapping("admin/user")
//@CrossOrigin //暂时通过此注解解决跨域问题，后续通过网关实现
public class AdminLoginController {

    /**
     * 登录请求，认证成功给客户端返回一个token 访问令牌
     * @return
     */
    @PostMapping("login")
    public R login(){
        return R.ok().data("token", UUID.randomUUID().toString().substring(8));
    }

    /**
     * 登录成功后获取用户信息
     * @return
     */
    @GetMapping("info")
    public R info(){
        return R.ok().data("roles","[admin]").data("name","admin").data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif?imageView2/1/w/80/h/80");
    }

    @PostMapping("logout")
    public R logout(){
        return R.ok();
    }

}
