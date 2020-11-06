package com.tingyu.tongmeng.edu.service.order.service;

import com.tingyu.tongmeng.edu.commons.dto.PayLogDTO;
import com.tingyu.tongmeng.edu.service.order.entity.PayLog;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 支付日志表 服务类
 * </p>
 *
 * @author 1218817610@qq.com
 * @since 2020-10-31
 */
public interface PayLogService extends IService<PayLog> {

    boolean save(PayLogDTO dto);
}
