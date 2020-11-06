package com.tingyu.tongmeng.edu.service.edu.vo;

import lombok.Data;

import java.util.List;

/**
 * @Author essionshy
 * @Create 2020/10/27 15:18
 * @Version tongmeng-edu
 */
@Data
public class SubjectVo {

    private String id;
    private String title;

    private String parentId;

    private List<SubjectVo> children;


}
