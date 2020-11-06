package com.tingyu.tongmeng.edu.service.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tingyu.tongmeng.edu.service.edu.dao.ChapterMapper;
import com.tingyu.tongmeng.edu.service.edu.entity.Chapter;
import com.tingyu.tongmeng.edu.service.edu.param.ChapterParam;
import com.tingyu.tongmeng.edu.service.edu.service.ChapterService;
import com.tingyu.tongmeng.edu.service.edu.service.VideoService;
import com.tingyu.tongmeng.edu.service.edu.vo.ChapterVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author 1218817610@qq.com
 * @since 2020-10-27
 */
@Service
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter> implements ChapterService {

    @Autowired
    private ChapterMapper chapterMapper;

    @Autowired
    private VideoService videoService;


    @Override
    public List<ChapterVo> listChaptersByCourseId(String courseId) {

        QueryWrapper<Chapter> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", courseId);
        List<Chapter> chapters = chapterMapper.selectList(wrapper);


        List<ChapterVo> chapterVoList = chapters.stream().map(chapter -> {
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(chapter, chapterVo);
            //为章节设置小节信息
            chapterVo.setChildren(videoService.getVideosByChapterId(chapter.getId()));
            return chapterVo;
        }).collect(Collectors.toList());


        return chapterVoList;
    }

    @Override
    public String save(ChapterParam param) {

        Chapter chapter = new Chapter();
        BeanUtils.copyProperties(param, chapter);
        chapterMapper.insert(chapter);
        String chapterId = chapter.getId();

        return chapterId;
    }

    @Override
    public boolean update(ChapterParam param) {
        Chapter chapter = new Chapter();
        BeanUtils.copyProperties(param, chapter);
        int count = chapterMapper.updateById(chapter);

        return count > 0;
    }

    @Override
    public boolean deleteById(String chapterId) {
        //如果该章节下面没有任何小节，则执行删除操作，否则返回false
        if(!videoService.hasVideoByChapterId(chapterId)){
            chapterMapper.deleteById(chapterId);
            return true;
        }else{
            return false;
        }

    }

    @Override
    public boolean deleteByCourseId(String courseId) {

        QueryWrapper<Chapter> wrapper=new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        int count = chapterMapper.delete(wrapper);
        return count>0;
    }


}
