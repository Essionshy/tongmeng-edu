package com.tingyu.tongmeng.edu.service.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tingyu.tongmeng.edu.service.edu.dao.VideoMapper;
import com.tingyu.tongmeng.edu.service.edu.entity.Video;
import com.tingyu.tongmeng.edu.service.edu.param.VideoParam;
import com.tingyu.tongmeng.edu.service.edu.service.VideoService;
import com.tingyu.tongmeng.edu.service.edu.vo.VideoVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author 1218817610@qq.com
 * @since 2020-10-27
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {


    @Autowired
    VideoMapper videoMapper;

    @Override
    public List<VideoVo> getVideosByChapterId(String chapterId) {
        QueryWrapper<Video> wrapper = new QueryWrapper<>();
        wrapper.eq("chapter_id", chapterId);
        List<Video> videos = videoMapper.selectList(wrapper);
        List<VideoVo> videoVoList = videos.stream().map(video -> {
            VideoVo videoVo = new VideoVo();
            BeanUtils.copyProperties(video, videoVo);
            return videoVo;
        }).collect(Collectors.toList());

        return videoVoList;
    }

    @Override
    public boolean hasVideoByChapterId(String chapterId) {
        return getVideosByChapterId(chapterId).size() > 0;
    }

    @Override
    public boolean save(VideoParam param) {
        Video video = new Video();
        BeanUtils.copyProperties(param,video);
        int count = videoMapper.insert(video);
        return count>0;
    }

    @Override
    public boolean update(VideoParam param) {
        Video video = new Video();
        BeanUtils.copyProperties(param,video);
        int count = videoMapper.updateById(video);
        return count>0;

    }

    @Override
    public boolean deleteByCourseId(String courseId) {
        QueryWrapper<Video> wrapper=new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        int deleteVideoCount = videoMapper.delete(wrapper);
        return deleteVideoCount>0;
    }

    @Override
    public List<Video> getVideosByCourseId(String courseId) {
        QueryWrapper<Video> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", courseId);
        List<Video> videos = videoMapper.selectList(wrapper);

        return videos;

    }


}
