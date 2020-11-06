package com.tingyu.tongmeng.edu.service.edu.service;

import com.tingyu.tongmeng.edu.service.edu.entity.Teacher;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * ??ʦ 服务类
 * </p>
 *
 * @author 1218817610@qq.com
 * @since 2020-10-25
 */
public interface TeacherService extends IService<Teacher> {

    Map<String, Object> getTeacherList(Integer page, Integer limit);
}
