package com.chasel.h5.dice.dao;

import com.chasel.h5.dice.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * @Author chasel
 * @Description TODO
 **/
@Mapper
public interface IH5DiceDao {

    Map<String, Object> getRecordingTimeByAccount(@Param("account") String account);

    Timestamp getTreasureBoxTimeBy(@Param("account") String account);

    Integer isFirstViewPrizes(@Param("account")String account);

    void changeIsTreasureBox(@Param("account") String account, @Param("code") int code,@Param("time") Timestamp timestamp);

    Integer maskLattice(@Param("account") String account);

    Integer questionLattice(@Param("account") String account);

    Map<String, Integer> queryPrizes(@Param("account") String account);

    void saveUserPhone(@Param("account")String account,@Param("phone") String phone,@Param("isFirstView") Integer isFirstView);

    String queryPhone(@Param("account") String account);

    String queryUser(@Param("account") String account);

    void insertUserPhone(@Param("account")String account,@Param("phone") String phone, @Param("isFirstView") Integer isFirstView);

    void saveUserDeliveryInfo(@Param("userVO") UserVO userVO);
}
