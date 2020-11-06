package com.tingyu.tongmeng.edu.service.edu.filter;

import com.alibaba.fastjson.JSONObject;
import com.tingyu.tongmeng.edu.service.edu.vo.front.CommentFrontVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @Author essionshy
 * @Create 2020/11/4 22:05
 * @Version tongmeng-edu
 */
@Slf4j
public class CommentRequest extends HttpServletRequestWrapper {


    private String[] words={"傻逼","日你妈","我操"};
    public CommentRequest(HttpServletRequest request) {
        super(request);
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        StringBuilder sb = new StringBuilder();
        ServletInputStream stream = super.getInputStream();
        BufferedReader reader=new BufferedReader(new InputStreamReader(stream));
        String tmp="";
        while ((tmp=reader.readLine())!=null){
            sb.append(tmp);
        }
        String body = sb.toString();
        log.info("body:{}",body);
        CommentFrontVo comment = JSONObject.parseObject(body, CommentFrontVo.class);
        String content = comment.getContent();
        log.info(" course comment content:{}",content);
        //调用字符过滤方法 TODO
        String newContent=filter(content);
        comment.setContent(newContent);
        String newBody = JSONObject.toJSONString(comment);
        log.info("newBody:{}",newBody);
        //指定字符编码
        InputStream is = IOUtils.toInputStream(newBody,"UTF-8");
        //构造新的ServletInputStream返回
        ServletInputStream servletInputStream = new ServletInputStream(){
            @Override
            public int read() throws IOException {
                return is.read();
            }

            @Override
            public boolean isFinished() {
                return false;
            }
            @Override
            public boolean isReady() {
                return false;
            }
            @Override
            public void setReadListener(ReadListener readListener) {
            }
        };
        return servletInputStream;
    }

    /**
     * 过滤
     * @param content
     * @return
     */
    private String filter(String content) {
        if("".equals(content)){
            return "";
        }
        for (String word:words){
            if(content.indexOf(word)==-1){
                continue;
            }else{
                content = content.replaceAll(word, "**");
            }
        }
        return content;
    }

    /**
     *
     * @return
     * @throws IOException
     */
    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(this.getInputStream()));
    }



}
