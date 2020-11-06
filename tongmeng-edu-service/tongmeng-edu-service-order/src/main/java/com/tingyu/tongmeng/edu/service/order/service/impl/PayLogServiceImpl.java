package com.tingyu.tongmeng.edu.service.order.service.impl;

import com.tingyu.tongmeng.edu.commons.dto.PayLogDTO;
import com.tingyu.tongmeng.edu.service.order.entity.PayLog;
import com.tingyu.tongmeng.edu.service.order.dao.PayLogMapper;
import com.tingyu.tongmeng.edu.service.order.service.PayLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 支付日志表 服务实现类
 * </p>
 *
 * @author 1218817610@qq.com
 * @since 2020-10-31
 */
@Service
public class PayLogServiceImpl extends ServiceImpl<PayLogMapper, PayLog> implements PayLogService {

    @Override
    public boolean save(PayLogDTO dto) {

        PayLog payLog = new PayLog();
        BeanUtils.copyProperties(dto, payLog);
        int count = baseMapper.insert(payLog);
        return count > 0;
    }
}
