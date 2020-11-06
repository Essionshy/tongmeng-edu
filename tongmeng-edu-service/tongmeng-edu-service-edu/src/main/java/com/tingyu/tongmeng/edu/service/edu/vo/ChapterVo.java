package com.tingyu.tongmeng.edu.service.edu.vo;

import lombok.Data;

import java.util.List;

/**
 * @Author essionshy
 * @Create 2020/10/28 10:22
 * @Version tongmeng-edu
 */
@Data
public class ChapterVo {

    private String id;

    private String title;

    private List<VideoVo> children;

}
