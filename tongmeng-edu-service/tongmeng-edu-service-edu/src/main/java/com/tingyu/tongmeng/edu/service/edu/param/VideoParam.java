package com.tingyu.tongmeng.edu.service.edu.param;

import lombok.Data;

/**
 * @Author essionshy
 * @Create 2020/10/28 22:51
 * @Version tongmeng-edu
 */
@Data
public class VideoParam {

    private String id;

    private String courseId;

    private String chapterId;

    private String title;

    private String videoSourceId;

    private String videoOriginalName;

    private Integer sort;

    private Long playCount;

    private Boolean isFree;

    private Float duration;

    private String status;

    private Long size;

    private Long version;



}
