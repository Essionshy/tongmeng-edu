package com.tingyu.tongmeng.edu.security.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tingyu.tongmeng.edu.commons.R;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author essionshy
 * @Create 2020/11/2 19:33
 * @Version tongmeng-edu
 */
public class ResponseUtil {
    public static void out(HttpServletResponse response, R r) {
        ObjectMapper mapper = new ObjectMapper();
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        try {
            mapper.writeValue(response.getWriter(), r);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
