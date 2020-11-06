package com.tingyu.tongmeng.edu.service.pay.controller;

import com.tingyu.tongmeng.edu.commons.R;
import com.tingyu.tongmeng.edu.service.pay.service.PayService;
import com.tingyu.tongmeng.edu.service.pay.utils.Constants;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author essionshy
 * @Create 2020/10/31 16:19
 * @Version tongmeng-edu
 */
@RestController
@RequestMapping("pay")
@Slf4j
public class PayController {

    @Autowired
    PayService payService;
    /**
     * 请求微信支付二维码地址
     * @return
     */

    @GetMapping("/wx/get/code/{orderNo}")
   public R getNative(@PathVariable("orderNo")String orderNo){

        Map<String, Object> map = payService.getNative(orderNo);
        log.info("result map:{}",map);
        return R.ok().data(map);
   }

   @ApiOperation("根据订单号查询微信支付状态")
   @GetMapping("/wx/get/status/{orderNo}")
   public R getPayStatus(@PathVariable("orderNo")String orderNo){
       Map<String,Object> map= payService.getPayStatus(orderNo);
       log.info("pay status map:{map}");
       //如果没有返回结果，则
        if(map==null){

            return R.error().message("支付错误");
        }
        if(map.get(Constants.WX_TRADE_STATE).equals("SUCCESS")){
            return  R.ok().message("支付成功");
        }
        //通过前端 设置的响应拦截该代码，不执行任何提示操作，客户端 setInterval(()=>{},3000)表示每隔3s请求一次查看支付状态
        return R.ok().code(25000).message("支付中");
   }

}
