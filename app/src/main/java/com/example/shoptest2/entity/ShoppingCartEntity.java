package com.example.shoptest2.entity;

import java.util.Objects;


public class ShoppingCartEntity {
    private String cartId;
    private String goodsId;
    private Integer buyNumber;
    private String userId;
    private GoodsEntity good;

    public GoodsEntity getGood() {
        return good;
    }

    public void setGood(GoodsEntity good) {
        this.good = good;
    }


    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }


    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }


    public Integer getBuyNumber() {
        return buyNumber;
    }

    public void setBuyNumber(Integer buyNumber) {
        this.buyNumber = buyNumber;
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
        ShoppingCartEntity that = (ShoppingCartEntity) o;
        return Objects.equals(cartId, that.cartId) &&
                Objects.equals(goodsId, that.goodsId) &&
                Objects.equals(buyNumber, that.buyNumber) &&
                Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cartId, goodsId, buyNumber, userId);
    }

    @Override
    public String toString() {
        return "ShoppingCartEntity{" +
                "cartId='" + cartId + '\'' +
                ", goodsId='" + goodsId + '\'' +
                ", buyNumber=" + buyNumber +
                ", userId='" + userId + '\'' +
                ", good=" + good.toString() +
                '}';
    }
}
