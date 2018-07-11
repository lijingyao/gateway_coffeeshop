package com.lijingyao.coffee.gateway.api.models;

import java.util.List;

/**
 * Created by lijingyao on 2018/7/9 16:45.
 */
public class UserCenterModel {

    private Long userId;
    private Long registeredTime;
    private String nickName;

    private List<UserSimpleOrderModel> orderModels;


    public List<UserSimpleOrderModel> getOrderModels() {
        return orderModels;
    }

    public void setOrderModels(List<UserSimpleOrderModel> orderModels) {
        this.orderModels = orderModels;
    }

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


}
