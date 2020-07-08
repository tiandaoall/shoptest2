package com.example.shoptest2.entity;

import java.util.Objects;


public class OrdersEntity {
    private String goodsOrderId;
    private String orderId;
    private String goodsName;
    private Double goodsPrices;
    private Integer buyNumber;
    private Double totalPrice;
    private String goodsBrand;
    private String goodsSummary;
    private String goodsImg;
    private Integer orderState;
    private String transactionDate;
    private String adderssIms;
    private String goodsId;
    private String userId;


    public String getGoodsOrderId() {
        return goodsOrderId;
    }

    public void setGoodsOrderId(String goodsOrderId) {
        this.goodsOrderId = goodsOrderId;
    }


    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }


    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }


    public Double getGoodsPrices() {
        return goodsPrices;
    }

    public void setGoodsPrices(Double goodsPrices) {
        this.goodsPrices = goodsPrices;
    }


    public Integer getBuyNumber() {
        return buyNumber;
    }

    public void setBuyNumber(Integer buyNumber) {
        this.buyNumber = buyNumber;
    }


    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }


    public String getGoodsBrand() {
        return goodsBrand;
    }

    public void setGoodsBrand(String goodsBrand) {
        this.goodsBrand = goodsBrand;
    }


    public String getGoodsSummary() {
        return goodsSummary;
    }

    public void setGoodsSummary(String goodsSummary) {
        this.goodsSummary = goodsSummary;
    }


    public String getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg;
    }


    public Integer getOrderState() {
        return orderState;
    }

    public void setOrderState(Integer orderState) {
        this.orderState = orderState;
    }


    public String getTransactionDate () {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }


    public String getAdderssIms() {
        return adderssIms;
    }

    public void setAdderssIms(String adderssIms) {
        this.adderssIms = adderssIms;
    }


    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrdersEntity that = (OrdersEntity) o;
        return Objects.equals(goodsOrderId, that.goodsOrderId) &&
                Objects.equals(orderId, that.orderId) &&
                Objects.equals(goodsName, that.goodsName) &&
                Objects.equals(goodsPrices, that.goodsPrices) &&
                Objects.equals(buyNumber, that.buyNumber) &&
                Objects.equals(totalPrice, that.totalPrice) &&
                Objects.equals(goodsBrand, that.goodsBrand) &&
                Objects.equals(goodsSummary, that.goodsSummary) &&
                Objects.equals(goodsImg, that.goodsImg) &&
                Objects.equals(orderState, that.orderState) &&
                Objects.equals(transactionDate, that.transactionDate) &&
                Objects.equals(adderssIms, that.adderssIms) &&
                Objects.equals(goodsId, that.goodsId) &&
                Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(goodsOrderId, orderId, goodsName, goodsPrices, buyNumber, totalPrice, goodsBrand, goodsSummary, goodsImg, orderState, transactionDate, adderssIms, goodsId, userId);
    }
}
