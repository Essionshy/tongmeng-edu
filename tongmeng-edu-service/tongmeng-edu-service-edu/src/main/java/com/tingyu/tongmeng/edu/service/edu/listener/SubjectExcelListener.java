package com.tingyu.tongmeng.edu.service.edu.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.tingyu.tongmeng.edu.service.edu.entity.Subject;
import com.tingyu.tongmeng.edu.service.edu.excel.SubjectData;
import com.tingyu.tongmeng.edu.service.edu.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author essionshy
 * @Create 2020/10/26 16:53
 * @Version tongmeng-edu
 */

@Component
public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {

    @Autowired
    SubjectService subjectService;
    //逐行读取Excel表格中的数据，不包含表头信息
    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        //添加一级分类，避免一级分类重复添加，添加前应判断要添加的名称是否已经存在
        if(!subjectService.existsFirstLevelSubject(subjectData.getFirstLevelSubjectName())){

            Subject firstLevelSubject=new Subject();
            firstLevelSubject.setTitle(subjectData.getFirstLevelSubjectName());
            firstLevelSubject.setSort(1);

            subjectService.save(firstLevelSubject);
        }

        Subject firstLevelSubject = subjectService.getFirstLevelSubjectByName(subjectData.getFirstLevelSubjectName());

        //添加二级分类
        if(!subjectService.existsSecondLevelSubject(subjectData.getSecondLevelSubjectName(),firstLevelSubject.getId())){

            Subject  secondLevelSubject=new Subject();

            secondLevelSubject.setParentId(firstLevelSubject.getId());
            secondLevelSubject.setTitle(subjectData.getSecondLevelSubjectName());
            secondLevelSubject.setSort(2);
            subjectService.save(secondLevelSubject);//保存二级分类

        }



    }

    //读取表头中的数据
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
