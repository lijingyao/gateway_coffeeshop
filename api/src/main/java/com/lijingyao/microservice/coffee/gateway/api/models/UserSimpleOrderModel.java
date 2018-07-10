package com.lijingyao.microservice.coffee.gateway.api.models;

/**
 * Created by lijingyao on 2018/7/9 23:28.
 */
public class UserSimpleOrderModel {

    private String orderId;

    private Long orderPrice;

    private Long createTime;


    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Long getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Long orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }


}
