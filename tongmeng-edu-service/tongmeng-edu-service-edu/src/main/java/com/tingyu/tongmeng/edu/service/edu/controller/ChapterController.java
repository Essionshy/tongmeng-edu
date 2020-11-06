package com.tingyu.tongmeng.edu.service.edu.controller;


import com.tingyu.tongmeng.edu.commons.R;
import com.tingyu.tongmeng.edu.service.edu.entity.Chapter;
import com.tingyu.tongmeng.edu.service.edu.param.ChapterParam;
import com.tingyu.tongmeng.edu.service.edu.service.ChapterService;
import com.tingyu.tongmeng.edu.service.edu.vo.ChapterVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author 1218817610@qq.com
 * @since 2020-10-27
 */
@RestController
@RequestMapping("/edu/chapter")

public class ChapterController {

    @Autowired
    private ChapterService chapterService;

    /**
     * 根据课程ID查询所有章节信息
     * @param courseId
     * @return
     */
    @GetMapping("list/{courseId}")
    public R list(@PathVariable String courseId){

        List<ChapterVo> chapters=chapterService.listChaptersByCourseId(courseId);

        return R.ok().data("chapters",chapters);
    }


    @PostMapping("save")
    public R save(@RequestBody ChapterParam param){
        String chapterId = chapterService.save(param);
        return R.ok().data("chapterId",chapterId);
    }

    @PutMapping("update")
    public R update(@RequestBody ChapterParam param){
        chapterService.update(param);
        return R.ok();
    }

    @DeleteMapping("delete/{chapterId}")
    public R delete(@PathVariable String chapterId){
        chapterService.deleteById(chapterId);
        return R.ok();
    }
    
    @GetMapping("get/{chapterId}")
    public R get(@PathVariable String chapterId){
        Chapter chapter = chapterService.getById(chapterId);
        return R.ok().data("chapter",chapter);
    }
}

