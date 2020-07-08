package com.example.shoptest2.entity;


import java.util.Objects;


public class AddresslistEntity {
    private String addressId;
    private String receiverName;
    private String receiverPhone;
    private String receiverAddress;
    private String userId;


    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }


    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }


    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
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
        AddresslistEntity that = (AddresslistEntity) o;
        return Objects.equals(addressId, that.addressId) &&
                Objects.equals(receiverName, that.receiverName) &&
                Objects.equals(receiverPhone, that.receiverPhone) &&
                Objects.equals(receiverAddress, that.receiverAddress) &&
                Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(addressId, receiverName, receiverPhone, receiverAddress, userId);
    }
}
