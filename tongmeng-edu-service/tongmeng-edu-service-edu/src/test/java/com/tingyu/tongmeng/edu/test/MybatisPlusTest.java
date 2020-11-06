package com.tingyu.tongmeng.edu.test;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tingyu.tongmeng.edu.service.TongmengApplication;
import com.tingyu.tongmeng.edu.service.edu.dao.TeacherMapper;
import com.tingyu.tongmeng.edu.service.edu.entity.Teacher;
import com.tingyu.tongmeng.edu.service.edu.service.TeacherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @Author essionshy
 * @Create 2020/10/26 7:51
 * @Version tongmeng-edu
 */
@SpringBootTest
@ContextConfiguration(classes = {TongmengApplication.class})
@WebAppConfiguration
public class MybatisPlusTest {

    @Autowired
    ApplicationContext context;

    @Autowired
    TeacherService teacherService;


    @Autowired
    TeacherMapper teacherMapper;

    @Test
    public void test(){
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        for (String name:beanDefinitionNames
             ) {
            System.out.println(name);
        }
    }

    @Test
    public void testPage(){
        Page<Teacher> page=new Page<>(1,2);
        teacherService.page(page,null);
        System.out.println(page.getTotal());
    }

    @Test
    public void testTeacherMapper(){
        Page<Teacher> page=new Page<>(1,2);
        teacherMapper.selectPage(page,null);
        System.out.println("查询总记录数："+page.getTotal());
    }


    @Test
    public void test002(){
        String content="你是个什么东西";
        byte[] bytes = content.getBytes();
        System.out.println(bytes.length);


    }
}
