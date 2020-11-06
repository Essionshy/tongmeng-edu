package com.tingyu.tongmeng.edu.service.rms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tingyu.tongmeng.edu.service.rms.entity.Banner;
import com.tingyu.tongmeng.edu.service.rms.param.BannerParam;
import com.tingyu.tongmeng.edu.service.rms.vo.BannerVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author 1218817610@qq.com
 * @since 2020-10-29
 */
public interface BannerService extends IService<Banner> {

    Map<String,Object> listByPage(Integer page, Integer limit, BannerParam param);

    boolean saveBanner(BannerParam param);

    boolean updateBanner(BannerParam param);

    List<BannerVo> getFrontBannerList();
}
