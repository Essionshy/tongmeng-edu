package com.tingyu.tongmeng.edu.service.edu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tingyu.tongmeng.edu.service.edu.entity.Subject;
import com.tingyu.tongmeng.edu.service.edu.vo.SubjectVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 1218817610@qq.com
 * @since 2020-10-26
 */
public interface SubjectService extends IService<Subject> {

    void addSubject(MultipartFile file);

    //查询一级分类
    Subject getFirstLevelSubjectByName(String firstLevelSubjectName);

    boolean existsFirstLevelSubject(String firstLevelSubjectName);

    boolean existsSecondLevelSubject(String secondLevelSubjectName,String pid);

    //查询二级分类
    Subject getSecondLevelSubjectByName(String secondLevelSubjectName, String pid);

    //获取课程分类的树形结构
    List<SubjectVo> listByTree();
}
