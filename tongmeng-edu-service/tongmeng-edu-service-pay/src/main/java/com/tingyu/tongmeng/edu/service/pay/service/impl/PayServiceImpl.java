package com.tingyu.tongmeng.edu.service.pay.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.wxpay.sdk.WXPayUtil;
import com.tingyu.tongmeng.edu.commons.dto.OrderDTO;
import com.tingyu.tongmeng.edu.commons.dto.PayLogDTO;
import com.tingyu.tongmeng.edu.commons.enums.PayType;
import com.tingyu.tongmeng.edu.service.pay.properties.WXPayProperties;
import com.tingyu.tongmeng.edu.service.pay.remote.OrderFeignService;
import com.tingyu.tongmeng.edu.service.pay.service.PayService;
import com.tingyu.tongmeng.edu.service.pay.utils.Constants;
import com.tingyu.tongmeng.edu.service.pay.utils.HttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author essionshy
 * @Create 2020/10/31 20:49
 * @Version tongmeng-edu
 */
@Service
public class PayServiceImpl implements PayService {

    @Autowired
    OrderFeignService orderFeignService;


    @Override
    public Map<String, Object> getNative(String orderNo) {
        Map map = new HashMap<>();



        //TODO 根据用户选择的支付方式进行支付
     /*   PayType payType = null;
        switch (payType){
            case WX:
                map=wxPayFeignService.createPayNative();
                break;
            case DEFAULT:

                break;
            case ALIPAY:
                break;
        }*/




        try {

            //1.根据订单号查询订单数据
            OrderDTO order = orderFeignService.getOrderDTO(orderNo);

            //2.封装查询参数 map 转化为 xml
            Map m = new HashMap();
            //1、设置支付参数
            m.put("appid", WXPayProperties.APP_ID);
            m.put("mch_id",WXPayProperties.MCH_ID);
            m.put("nonce_str", WXPayUtil.generateNonceStr());
            m.put("body", order.getCourseTitle());
            m.put("out_trade_no", orderNo);
            m.put("total_fee", order.getTotalFee().multiply(new BigDecimal("100")).longValue() + "");
            m.put("spbill_create_ip", WXPayProperties.SPBILL_CREATE_IP);
            m.put("notify_url", WXPayProperties.NOTIFY_URL);
            m.put("trade_type", WXPayProperties.TRADE_TYPE);

            //2、HTTPClient来根据URL访问第三方接口并且传递参数
            HttpClient client = new HttpClient(WXPayProperties.CODE_REQUEST_URL);

            //client设置参数
            client.setXmlParam(WXPayUtil.generateSignedXml(m, WXPayProperties.BZ_NUM));
            client.setHttps(true);
            client.post();
            //3、返回第三方的数据
            String xml = client.getContent();
            Map<String, String> resultMap = WXPayUtil.xmlToMap(xml);
            //4、封装返回结果集


            map.put("order_no", orderNo);
            map.put("course_id", order.getCourseId());
            map.put("total_fee", order.getTotalFee());
            map.put("result_code", resultMap.get(Constants.WX_RESULT_CODE));
            map.put("code_url", resultMap.get(Constants.WX_CODE_URL));

            //微信支付二维码2小时过期，可采取2小时未支付取消订单
            //redisTemplate.opsForValue().set(orderNo, map, 120, TimeUnit.MINUTES);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;


    }

    @Override
    public Map<String, Object> getPayStatus(String orderNo) {

        Map<String, Object> map = new HashMap<>();
        try {
            //1、封装参数
            Map m = new HashMap<>();
            m.put("appid", WXPayProperties.APP_ID);
            m.put("mch_id", WXPayProperties.MCH_ID);
            m.put("out_trade_no", orderNo);
            m.put("nonce_str", WXPayUtil.generateNonceStr());

            //2、设置请求
            HttpClient client = new HttpClient(WXPayProperties.STATUS_REQUEST_URL);
            client.setXmlParam(WXPayUtil.generateSignedXml(m, WXPayProperties.BZ_NUM));
            client.setHttps(true);
            client.post();
            //3、返回第三方的数据
            String xml = client.getContent();
            Map<String, String> resultMap = WXPayUtil.xmlToMap(xml);
            //6、转成Map


            for (Map.Entry<String,String> entry: resultMap.entrySet()){


                map.put(entry.getKey(),entry.getValue());

            }

            //7.判断订单是否支付成功，如果是则修改订单状态，异步调用
            if(resultMap.get("trade_state").equals("SUCCESS")){

                //修改订单状态
                orderFeignService.updateOrderStatus(orderNo);
                OrderDTO order = orderFeignService.getOrderDTO(orderNo);


                //保存支付记录
                PayLogDTO payLog = new PayLogDTO();
                payLog.setOrderNo(orderNo);
                payLog.setPayTime(new Date());
                payLog.setPayType(PayType.DEFAULT.getValue());
                payLog.setTotalFee(order.getTotalFee());
                payLog.setTradeState(resultMap.get(Constants.WX_TRADE_STATE));
                payLog.setTransactionId(resultMap.get(Constants.WX_TRANSACTION_ID));
                payLog.setAttr(JSONObject.toJSONString(resultMap));//微信支付返回的其他信息
                orderFeignService.savePayLog(payLog);

            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        //7、返回
        return map;
    }
}
