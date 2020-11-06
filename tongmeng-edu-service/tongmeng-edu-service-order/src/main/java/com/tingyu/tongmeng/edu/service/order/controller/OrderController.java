package com.tingyu.tongmeng.edu.service.order.controller;


import com.tingyu.tongmeng.edu.commons.JwtUtils;
import com.tingyu.tongmeng.edu.commons.R;
import com.tingyu.tongmeng.edu.commons.dto.OrderDTO;
import com.tingyu.tongmeng.edu.service.order.service.OrderService;
import com.tingyu.tongmeng.edu.service.order.vo.OrderVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author 1218817610@qq.com
 * @since 2020-10-31
 */
@RestController
@RequestMapping("/order")

public class OrderController {

    @Autowired
    private OrderService orderService;

    @ApiOperation("根据课程ID创建并返回订单号")
    @PostMapping("save/{courseId}")
    public R save(@PathVariable("courseId")String courseId, HttpServletRequest request){
        String orderNo = orderService.createOrder(courseId, JwtUtils.getUserIdByToken(request));
        return R.ok().data("orderNo",orderNo);
    }


    @ApiOperation("根据订单号查询订单信息")
    @GetMapping("get/{orderNo}")
    public R get(@PathVariable("orderNo")String orderNo){
        OrderVo order = orderService.getByOrderNo(orderNo);
        return R.ok().data("order",order);
    }

    @ApiOperation("对外提供的api根据订单号更新订单状态")
    @PutMapping("/update/remote/status/{orderNo}")
    public void updateOrderStatus(@PathVariable String orderNo){
        orderService.updateOrderStatus(orderNo);
    }

    @ApiOperation("对外提供api根据订单号查询订单信息")
    @GetMapping("get/remote/{orderNo}")
    public OrderDTO getOrderDTO(@PathVariable("orderNo")String orderNo){
        OrderDTO order = orderService.getOrderDTOByOrderNo(orderNo);
        return order;
    }

    @ApiOperation("查询订单状态是否已经购买")
    @GetMapping("course/status/{courseId}/{memberId}")
    public boolean isBuy(@PathVariable("courseId")String courseId,@PathVariable("memberId")String memberId){

        return   orderService.isBuy(courseId,memberId);
    }

}

