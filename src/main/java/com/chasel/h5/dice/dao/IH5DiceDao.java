package com.chasel.h5.dice.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.Map;

/**
 * @Author chasel
 * @Description TODO
 **/
@Mapper
public interface IH5DiceDao {

    Map<String, Object> getRecordingTimeByAccount(@Param("account") String account);

    Timestamp getTreasureBoxTimeBy(@Param("account") String account);
}
