package com.tingyu.tongmeng.edu.commons.enums;

/**
 * @Author essionshy
 * @Create 2020/10/31 16:31
 * @Version tongmeng-edu
 */
public enum PayType {

    DEFAULT(1),
    WX(0),
    ALIPAY(1);

    private int value;

    PayType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
