package com.tingyu.tongmeng.edu.service.order.service;

import com.tingyu.tongmeng.edu.commons.dto.OrderDTO;
import com.tingyu.tongmeng.edu.service.order.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tingyu.tongmeng.edu.service.order.vo.OrderVo;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author 1218817610@qq.com
 * @since 2020-10-31
 */
public interface OrderService extends IService<Order> {

    /**
     * 根据课程ID创建订单并返回订单号
     * @param courseId
     * @param userIdByToken
     * @return
     */
    String createOrder(String courseId, String userIdByToken);

    OrderVo getByOrderNo(String orderNo);

    void updateOrderStatus(String orderNo);

    OrderDTO getOrderDTOByOrderNo(String orderNo);

    boolean isBuy(String courseId, String memberId);
}
