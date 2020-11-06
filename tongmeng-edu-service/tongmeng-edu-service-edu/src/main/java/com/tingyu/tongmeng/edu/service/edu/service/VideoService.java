package com.tingyu.tongmeng.edu.service.edu.service;

import com.tingyu.tongmeng.edu.service.edu.entity.Video;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tingyu.tongmeng.edu.service.edu.param.VideoParam;
import com.tingyu.tongmeng.edu.service.edu.vo.VideoVo;

import java.util.List;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author 1218817610@qq.com
 * @since 2020-10-27
 */
public interface VideoService extends IService<Video> {

    List<VideoVo> getVideosByChapterId(String chapterId);

    boolean hasVideoByChapterId(String chapterId);

    boolean save(VideoParam param);

    boolean update(VideoParam param);

    boolean deleteByCourseId(String courseId);

    List<Video> getVideosByCourseId(String courseId);
}
