package com.chasel.h5.dice.dao;

import com.chasel.h5.dice.vo.WechatUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface WechatUserDao {
    /**
     * 根据openid返回微信用户详情
     *
     * @param openid openid
     * @return 微信用户详情
     */
    public abstract WechatUser queryByOpenid(@Param("openid") String openid);

    /**
     * 更新微信用户信息，如果存在相同openid的记录，则更新其他信息，否则插入新的记录
     *
     * @param wechatUser 微信用户详情
     * @return 操作记录条数
     */
    public abstract int update(WechatUser wechatUser);

    /**
     * 根据openid删除微信用户详情
     *
     * @param openid openid
     * @return 操作记录条数
     */
    public abstract int deleteByOpenid(@Param("openid") String openid);
}
