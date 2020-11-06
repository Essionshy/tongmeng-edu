package com.tingyu.tongmeng.edu.service.edu.service;

import com.tingyu.tongmeng.edu.service.edu.entity.Chapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tingyu.tongmeng.edu.service.edu.param.ChapterParam;
import com.tingyu.tongmeng.edu.service.edu.vo.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author 1218817610@qq.com
 * @since 2020-10-27
 */
public interface ChapterService extends IService<Chapter> {

    List<ChapterVo> listChaptersByCourseId(String courseId);


    //保存章节并返回ID
    String save(ChapterParam param);

    boolean update(ChapterParam param);

    //根据章节ID删除，如果该章节下面有小节则提示不能删除
    boolean deleteById(String chapterId);

    boolean deleteByCourseId(String courseId);
}
