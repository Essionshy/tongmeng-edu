package com.tingyu.tongmeng.edu.service.edu.param;

import lombok.Data;

/**
 * @Author essionshy
 * @Create 2020/10/25 20:47
 * @Version tongmeng-edu
 */
@Data
public class TeacherParam  {

    private Long page;

    private Long limit;

    private String name;

    private Integer level;

}
