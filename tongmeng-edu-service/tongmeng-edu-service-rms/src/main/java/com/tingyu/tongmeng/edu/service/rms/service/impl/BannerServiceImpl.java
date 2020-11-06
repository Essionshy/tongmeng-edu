package com.tingyu.tongmeng.edu.service.rms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tingyu.tongmeng.edu.service.rms.dao.BannerMapper;
import com.tingyu.tongmeng.edu.service.rms.entity.Banner;
import com.tingyu.tongmeng.edu.service.rms.param.BannerParam;
import com.tingyu.tongmeng.edu.service.rms.service.BannerService;
import com.tingyu.tongmeng.edu.service.rms.vo.BannerVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author 1218817610@qq.com
 * @since 2020-10-29
 */
@Service
public class BannerServiceImpl extends ServiceImpl<BannerMapper, Banner> implements BannerService {

    @Autowired
    BannerMapper bannerMapper;

    @Override
    public Map<String, Object> listByPage(Integer page, Integer limit, BannerParam param) {
        HashMap<String, Object> map = new HashMap<>();
        QueryWrapper<Banner> wrapper = new QueryWrapper<>();
        //封装查询条件

        Page<Banner> bannerPage = new Page<>(page, limit);
        bannerMapper.selectPage(bannerPage, wrapper);

        long total = bannerPage.getTotal();
        List<Banner> bannerList = bannerPage.getRecords();
        map.put("total", total);
        map.put("bannerList", bannerList);
        return map;
    }

    @Override
    public boolean saveBanner(BannerParam param) {
        Banner banner = new Banner();
        BeanUtils.copyProperties(param, banner);
        int count = bannerMapper.insert(banner);
        return count > 0;
    }

    @Override
    public boolean updateBanner(BannerParam param) {
        Banner banner = new Banner();
        BeanUtils.copyProperties(param,banner);
        int count = bannerMapper.updateById(banner);
        return count>0;
    }

    /**
     * 返回前台显示的2条banner数据
     *
     * @return
     */
    @Cacheable(value = "banner",key = "'getFrontBannerList'")
    @Override
    public List<BannerVo> getFrontBannerList() {
        QueryWrapper<Banner> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("gmt_create");//返回最新的两条数据
        wrapper.last("limit 2");
        List<Banner> banners = bannerMapper.selectList(wrapper);
        //将Banner转换为BannerVo
        List<BannerVo> bannerVos = banners.stream().map(banner -> {
            BannerVo bannerVo = new BannerVo();
            BeanUtils.copyProperties(banner, bannerVo);
            return bannerVo;
        }).collect(Collectors.toList());
        return bannerVos;
    }
}
