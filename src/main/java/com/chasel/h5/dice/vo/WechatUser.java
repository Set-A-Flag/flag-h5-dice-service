package com.chasel.h5.dice.vo;

import org.apache.ibatis.type.Alias;

/**
 * 微信用户
 */
@Alias("WechatUser")
public class WechatUser {
    //微信用户openid
    private String openid;
    //微信用户头像url
    private String headimgurl;
    //微信用户所属国家
    private String country;
    //微信用户所属省份
    private String province;
    //微信用户所属城市
    private String city;
    //微信用户语言类型
    private String language;
    //微信用户性别
    private int sex;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "WechatUser{" +
                "openid='" + openid + '\'' +
                ", headimgurl='" + headimgurl + '\'' +
                ", country='" + country + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", language='" + language + '\'' +
                ", sex=" + sex +
                '}';
    }
}
