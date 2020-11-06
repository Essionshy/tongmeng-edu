package com.tingyu.tongmeng.edu.service.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tingyu.tongmeng.edu.commons.ResultException;
import com.tingyu.tongmeng.edu.commons.dto.OrderCourseDTO;
import com.tingyu.tongmeng.edu.commons.dto.OrderDTO;
import com.tingyu.tongmeng.edu.commons.dto.OrderMemberDTO;
import com.tingyu.tongmeng.edu.service.order.dao.OrderMapper;
import com.tingyu.tongmeng.edu.service.order.entity.Order;
import com.tingyu.tongmeng.edu.commons.enums.PayStatus;
import com.tingyu.tongmeng.edu.commons.enums.PayType;
import com.tingyu.tongmeng.edu.service.order.remote.CourseFeignService;
import com.tingyu.tongmeng.edu.service.order.remote.MemberFeignService;
import com.tingyu.tongmeng.edu.service.order.service.OrderService;
import com.tingyu.tongmeng.edu.service.order.utils.OrderNoGeneratorUtils;
import com.tingyu.tongmeng.edu.service.order.vo.OrderVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author 1218817610@qq.com
 * @since 2020-10-31
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {


    @Autowired
    private MemberFeignService memberFeignService;

    @Autowired
    private CourseFeignService courseFeignService;

    @Override
    public String createOrder(String courseId, String userIdByToken) {

        //根据用户ID和课程ID判断订单是否创建，已经创建则提示用户已经支付，避免同一用户多次创建同一订单
        //1.远程调用课程服务，根据课程ID获取课程信息
        OrderCourseDTO course = courseFeignService.getOrderCourseDTO(courseId);
        //2.远程调用会员服务根据会员ID获取会员信息
        OrderMemberDTO member = memberFeignService.getOrderMember(userIdByToken);
        //创建Order对象并
        Order order = new Order();
        if (course != null) {
            order.setCourseCover(course.getCover());
            order.setCourseId(course.getId());
            order.setCourseTitle(course.getTitle());
            order.setTeacherName(course.getTeacherName());
            order.setTotalFee(course.getPrice());

            if (member != null) {
                order.setMemberId(member.getId());
                order.setMobile(member.getMobile());
                order.setNickname(member.getNickname());
                if (exists(courseId, member.getId())) {
                    throw new ResultException(30001, "订单已经创建，请不要重复创建，直接去到订单页面进行支付就可以了");
                }

            }
        }
        //生成订单编号  TODO 通过分布式锁生成唯一订单
        String orderNo = OrderNoGeneratorUtils.create();
        order.setOrderNo(orderNo);
        order.setPayType(PayType.DEFAULT.getValue()); //支付类型，默认微信支付
        order.setStatus(PayStatus.NOTPAY.getValue()); //支付状态 0 未支付，1 已支付


        //保存订单
        baseMapper.insert(order);

        return orderNo;
    }

    private boolean exists(String courseId, String memberId) {

        QueryWrapper<Order> wrapper = new QueryWrapper<>();

        if (!StringUtils.isEmpty(courseId) && !StringUtils.isEmpty(memberId)) {
            wrapper.eq("course_id", courseId).and((queryWrapper) -> {
                queryWrapper.eq("member_id", memberId);
            });
        }


        Integer count = baseMapper.selectCount(wrapper);

        return count > 0;

    }

    @Override
    public OrderVo getByOrderNo(String orderNo) {
        Order order = getOrder(orderNo);
        OrderVo orderVo = new OrderVo();
        if (order != null) {
            BeanUtils.copyProperties(order, orderVo);
        }
        return orderVo;
    }

    @Override
    public void updateOrderStatus(String orderNo) {

        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("order_no", orderNo);
        Order order = baseMapper.selectOne(wrapper);
        order.setStatus(PayStatus.PAYED.getValue());//修改订单状态为已支付
        baseMapper.updateById(order);
    }

    @Override
    public OrderDTO getOrderDTOByOrderNo(String orderNo) {
        Order order = getOrder(orderNo);
        OrderDTO orderDTO = new OrderDTO();
        if (order != null) {
            BeanUtils.copyProperties(order, orderDTO);
        }
        return orderDTO;
    }

    @Override
    public boolean isBuy(String courseId, String memberId) {

        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", courseId);
        wrapper.eq("member_id", memberId);
        wrapper.eq("status", PayStatus.PAYED.getValue());
        Integer count = baseMapper.selectCount(wrapper);

        return count > 0;
    }


    private Order getOrder(String orderNo) {
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(orderNo)) {
            wrapper.eq("order_no", orderNo);
        }
        return baseMapper.selectOne(wrapper);
    }
}
