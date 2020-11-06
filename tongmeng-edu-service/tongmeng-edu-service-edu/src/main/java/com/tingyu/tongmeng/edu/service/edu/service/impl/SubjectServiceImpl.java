package com.tingyu.tongmeng.edu.service.edu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tingyu.tongmeng.edu.service.edu.dao.SubjectMapper;
import com.tingyu.tongmeng.edu.service.edu.entity.Subject;
import com.tingyu.tongmeng.edu.service.edu.excel.SubjectData;
import com.tingyu.tongmeng.edu.service.edu.listener.SubjectExcelListener;
import com.tingyu.tongmeng.edu.service.edu.service.SubjectService;
import com.tingyu.tongmeng.edu.service.edu.vo.SubjectVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 1218817610@qq.com
 * @since 2020-10-26
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {

    @Autowired
    SubjectExcelListener subjectExcelListener;

    @Autowired
    SubjectMapper subjectMapper;

    @Override
    public void addSubject(MultipartFile file) {
        //读取上传的Excel文件

        try {
            //
            EasyExcel.read(file.getInputStream(), SubjectData.class, subjectExcelListener).sheet().doRead();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     * 根据课程分类名称查询课程
     *
     * @param subjectName
     * @return
     */
    @Override
    public Subject getFirstLevelSubjectByName(String subjectName) {
        QueryWrapper<Subject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", subjectName);
        wrapper.eq("parent_id", 0);
        Subject subject = subjectMapper.selectOne(wrapper);
        return subject;
    }

    @Override
    public boolean existsFirstLevelSubject(String firstLevelSubjectName) {
        return getFirstLevelSubjectByName(firstLevelSubjectName) != null;
    }

    @Override
    public boolean existsSecondLevelSubject(String secondLevelSubjectName, String pid) {
        return getSecondLevelSubjectByName(secondLevelSubjectName, pid) != null;
    }

    public Subject getSecondLevelSubjectByName(String secondLevelSubjectName, String pid) {
        QueryWrapper<Subject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", secondLevelSubjectName);
        wrapper.eq("parent_id", pid);
        Subject subject = subjectMapper.selectOne(wrapper);
        return subject;
    }

    @Override
    public List<SubjectVo> listByTree() {

        //获取所有的课程分类
        List<Subject> subjects = list();



        //将Subject转换为SubjectVO
        List<SubjectVo> subjectVoList = subjects.stream().map(subject -> {
            SubjectVo subjectVO = new SubjectVo();
            BeanUtils.copyProperties(subject, subjectVO); //
            return subjectVO;
        }).collect(Collectors.toList());

        //构建Tree

        List<SubjectVo> result = subjectVoList.stream().filter(subjectVo -> {
            return "0".equals(subjectVo.getParentId());
        }).map(subjectVo -> {
            subjectVo.setChildren(getChildren(subjectVo, subjectVoList));
            return subjectVo;
        }).collect(Collectors.toList());

        return result;
    }



    //获取所有子权限
    private List<SubjectVo> getChildren(SubjectVo root, List<SubjectVo> all) {
        List<SubjectVo> children = all.stream().filter(subjectVo -> {
            return subjectVo.getParentId().equals(root.getId()) ;
        }).map(subjectVo -> {
            subjectVo.setChildren(getChildren(subjectVo, all));
            return subjectVo;
        }).collect(Collectors.toList());
        return children;
    }
}
