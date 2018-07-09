package com.lijingyao.microservice.coffee.gateway.api.models;

import java.util.List;

/**
 * Created by lijingyao on 2018/7/9 22:00.
 */
public class OrderCreateModel {

    private List<OrderCreateDetailModel> details;
    private Long userId;

    public List<OrderCreateDetailModel> getDetails() {
        return details;
    }

    public void setDetails(List<OrderCreateDetailModel> details) {
        this.details = details;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
