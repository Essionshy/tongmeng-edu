package com.tingyu.tongmeng.edu.service.acl.test;

import com.tingyu.tongmeng.edu.commons.MD5Utils;
import com.tingyu.tongmeng.edu.security.utils.TokenManager;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author essionshy
 * @Create 2020/11/3 9:41
 * @Version tongmeng-edu
 */
@SpringBootTest
public class TCLTest {

    @Test
    public void test(){

        String password="111111";
        String encrypt = MD5Utils.encrypt(password);
        System.out.println(encrypt);


        String username="admin";
        TokenManager tokenManager = new TokenManager();
        String token = tokenManager.createToken(username);
        System.out.println(token);

        String userFromToken = tokenManager.getUserFromToken(token);
        System.out.println(userFromToken);

    }
}
