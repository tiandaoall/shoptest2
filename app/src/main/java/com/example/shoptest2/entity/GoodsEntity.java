package com.example.shoptest2.entity;


import java.util.Objects;


public class GoodsEntity {
    private String goodsId;
    private String goodsName;
    private Double goodsPrices;
    private Integer stock;
    private String goodsBrand;
    private String goodsSummary;
    private String goodsClass;
    private String goodsImg;
    private Integer salesvolume;
    private Integer state;


    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
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


    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
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


    public String getGoodsClass() {
        return goodsClass;
    }

    public void setGoodsClass(String goodsClass) {
        this.goodsClass = goodsClass;
    }


    public String getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg;
    }


    public Integer getSalesvolume() {
        return salesvolume;
    }

    public void setSalesvolume(Integer salesvolume) {
        this.salesvolume = salesvolume;
    }


    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GoodsEntity that = (GoodsEntity) o;
        return Objects.equals(goodsId, that.goodsId) &&
                Objects.equals(goodsName, that.goodsName) &&
                Objects.equals(goodsPrices, that.goodsPrices) &&
                Objects.equals(stock, that.stock) &&
                Objects.equals(goodsBrand, that.goodsBrand) &&
                Objects.equals(goodsSummary, that.goodsSummary) &&
                Objects.equals(goodsClass, that.goodsClass) &&
                Objects.equals(goodsImg, that.goodsImg) &&
                Objects.equals(salesvolume, that.salesvolume) &&
                Objects.equals(state, that.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(goodsId, goodsName, goodsPrices, stock, goodsBrand, goodsSummary, goodsClass, goodsImg, salesvolume, state);
    }

    @Override
    public String toString() {
        return "GoodsEntity{" +
                "goodsId='" + goodsId + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", goodsPrices=" + goodsPrices +
                ", stock=" + stock +
                ", goodsBrand='" + goodsBrand + '\'' +
                ", goodsSummary='" + goodsSummary + '\'' +
                ", goodsClass='" + goodsClass + '\'' +
                ", goodsImg='" + goodsImg + '\'' +
                ", salesvolume=" + salesvolume +
                ", state=" + state +
                '}';
    }
}
