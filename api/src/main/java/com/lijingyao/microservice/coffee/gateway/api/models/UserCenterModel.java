package com.lijingyao.microservice.coffee.gateway.api.models;

/**
 * Created by lijingyao on 2018/7/9 16:45.
 */
public class UserCenterModel {

    private Long userId;
    private Long registeredTime;
    private String nickName;

    private String orderTitle;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRegisteredTime() {
        return registeredTime;
    }

    public void setRegisteredTime(Long registeredTime) {
        this.registeredTime = registeredTime;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getOrderTitle() {
        return orderTitle;
    }

    public void setOrderTitle(String orderTitle) {
        this.orderTitle = orderTitle;
    }
}
