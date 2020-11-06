package com.tingyu.tongmeng.edu.commons.enums;

/**
 * @Author essionshy
 * @Create 2020/10/31 16:32
 * @Version tongmeng-edu
 */
public enum PayStatus {

    NOTPAY(0),
    PAYED(1);

    private int value;

    PayStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
