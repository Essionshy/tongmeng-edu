package com.tingyu.tongmeng.edu.service.edu.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @Author essionshy
 * @Create 2020/10/26 16:46
 * @Version tongmeng-edu
 */
@Data
public class SubjectData {

    //一级分类名称
    @ExcelProperty(index = 0)
    private String firstLevelSubjectName;

    //二级分类名称
    @ExcelProperty(index = 1)
    private String secondLevelSubjectName;

}
