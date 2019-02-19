package com.chasel.h5.dice.vo;

/**
 * @Author chasel
 * @Description TODO
 **/
public class UserVO {

    private String account;

    private String name;

    private String receivingCall;

    private String address;

    public UserVO() {
    }

    public UserVO(String account, String name, String receivingCall, String address) {
        this.account = account;
        this.name = name;
        this.receivingCall = receivingCall;
        this.address = address;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReceivingCall() {
        return receivingCall;
    }

    public void setReceivingCall(String receivingCall) {
        this.receivingCall = receivingCall;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "UserVO{" +
                "account='" + account + '\'' +
                ", name='" + name + '\'' +
                ", receivingCall='" + receivingCall + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
