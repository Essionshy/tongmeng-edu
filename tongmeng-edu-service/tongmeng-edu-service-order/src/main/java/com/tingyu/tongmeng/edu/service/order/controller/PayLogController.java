package com.tingyu.tongmeng.edu.service.order.controller;


import com.tingyu.tongmeng.edu.commons.dto.PayLogDTO;
import com.tingyu.tongmeng.edu.service.order.service.PayLogService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 支付日志表 前端控制器
 * </p>
 *
 * @author 1218817610@qq.com
 * @since 2020-10-31
 */
@RestController
@RequestMapping("/order/pay/log")
@Slf4j
public class PayLogController {

    @Autowired
    PayLogService payLogService;

    @ApiOperation("保存支付记录")
    @PostMapping("save")
    public void savePayLog(@RequestBody PayLogDTO dto){

       if(payLogService.save(dto)){
           log.info("支付记录日志：订单号【{}】保存成功。",dto.getOrderNo());
       }else{
           log.info("支付记录日志：订单号【{}】保存失败。",dto.getOrderNo());
       }
    }

}

