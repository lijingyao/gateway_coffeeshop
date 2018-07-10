package com.lijingyao.microservice.coffee.gateway.api.models;

import java.util.List;

/**
 * Created by lijingyao on 2018/7/9 22:00.
 */
public class OrderModel {

    private List<OrderDetailModel> details;
    private Long createTime;
    private String orderId;
    private Long totalPrice;


    public List<OrderDetailModel> getDetails() {
        return details;
    }

    public void setDetails(List<OrderDetailModel> details) {
        this.details = details;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }
}
