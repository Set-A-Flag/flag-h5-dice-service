package com.chasel.h5.dice.vo;

import org.apache.ibatis.type.Alias;

@Alias("owner")
public class OwnerVO {

    private String account;

    private String name;

    private String phone;

    private String address;

    private String storeName;

    private String attributableAgent;

    public OwnerVO() {
    }

    public OwnerVO(String name, String phone, String address, String storeName, String attributableAgent) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.storeName = storeName;
        this.attributableAgent = attributableAgent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getAttributableAgent() {
        return attributableAgent;
    }

    public void setAttributableAgent(String attributableAgent) {
        this.attributableAgent = attributableAgent;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "OwnerVO{" +
                "account='" + account + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", storeName='" + storeName + '\'' +
                ", attributableAgent='" + attributableAgent + '\'' +
                '}';
    }
}
