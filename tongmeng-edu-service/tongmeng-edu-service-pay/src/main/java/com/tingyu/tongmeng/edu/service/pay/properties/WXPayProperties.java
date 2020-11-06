package com.tingyu.tongmeng.edu.service.pay.properties;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author essionshy
 * @Create 2020/10/31 21:42
 * @Version tongmeng-edu
 */
@Component
public class WXPayProperties implements InitializingBean {
    /**
     * wx.pay.bz_num=T6m9iK73b0kn9g5v426MKfHQH7X8rKwb
     * #请求支付二维码地址
     * wx.pay.code_request_url=https://api.mch.weixin.qq.com/pay/unifiedorder
     * #请求支付状态地址
     * wx.pay.status_reqeust_url=
     */
    @Value("${wx.pay.app_id}")
    private String app_id;
    @Value("${wx.pay.mch_id}")
    private String mch_id;
    @Value("${wx.pay.spbill_create_ip}")
    private String spbill_create_ip;
    @Value("${wx.pay.notify_url}")
    private String notify_url;
    @Value("${wx.pay.trade_type}")
    private String trade_type;
    @Value("${wx.pay.code_request_url}")
    private String code_request_url;
    @Value("${wx.pay.status_request_url}")
    private String status_request_url;
    @Value("${wx.pay.bz_num}")
    private String bz_num;


    public static String APP_ID;
    public static String MCH_ID;
    public static String SPBILL_CREATE_IP;
    public static String NOTIFY_URL;
    public static String TRADE_TYPE;
    public static String CODE_REQUEST_URL;
    public static String STATUS_REQUEST_URL;
    public static String BZ_NUM;


    @Override
    public void afterPropertiesSet() throws Exception {
        APP_ID = this.app_id;
        MCH_ID = this.mch_id;
        SPBILL_CREATE_IP = this.spbill_create_ip;
        NOTIFY_URL = this.notify_url;
        TRADE_TYPE = this.trade_type;
        CODE_REQUEST_URL=this.code_request_url;
        STATUS_REQUEST_URL=this.status_request_url;
        BZ_NUM=this.bz_num;
    }
}
