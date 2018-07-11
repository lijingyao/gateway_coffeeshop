package com.lijingyao.coffee.gateway.api.models;

/**
 * Created by lijingyao on 2018/7/9 22:05.
 */
public class OrderCreateDetailModel {


    private Integer itemId;

    // 重量或者是数量
    private Long quantity;

    // 糖分
    private String sugar;

    // 牛奶含量
    private String milk;

    // 咖啡浓度，附加
    private int espresso;

    // 咖啡因含量
    private String coffeine;

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getSugar() {
        return sugar;
    }

    public void setSugar(String sugar) {
        this.sugar = sugar;
    }

    public String getMilk() {
        return milk;
    }

    public void setMilk(String milk) {
        this.milk = milk;
    }

    public int getEspresso() {
        return espresso;
    }

    public void setEspresso(int espresso) {
        this.espresso = espresso;
    }

    public String getCoffeine() {
        return coffeine;
    }

    public void setCoffeine(String coffeine) {
        this.coffeine = coffeine;
    }
}
