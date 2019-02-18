package com.chasel.h5.dice.service;

import com.chasel.h5.dice.vo.OwnerVO;

import java.util.List;

public interface IH5DiceService {

    /**
     * @Author chasel
     * @Description 店主报名接口
     *      1、校验报名截止时间
     *      2、校验是否已经报名
     *      3、地区的省市县应该用-拼接，为了转发数详情页省份截取
     * @Date 2019/2/18 22:40
     * @Param [ownerVO]
     * @return void
     **/
    void RegisterStoreOwner(OwnerVO ownerVO);

    /**
     * @Author chasel
     * @Description 检查是否有游戏次数：true是，false否
     *      1、获取数据库字段recording_time记录时间
     *      2、与当前时间小时比较：
     *          相等 -> 则判断number_of_games游戏次数是否等于3：是(提示：请下个小时再来玩),否true
     *          不等 -> 则有3次机会
     * @Date 2019/2/18 22:41
     * @Param [name]
     * @return boolean
     **/
    boolean checkNumOfGames(String name);

    /**
     * @Author chasel
     * @Description 保存用户游戏分数
     *      1、为了严谨，调用checkNumOfGames方法校验
     *      2、23:00~7:00时间段内，活动结束时间后，直接return，不做以下操作
     *      3、获取总分total，然后和score相加保存
     *      4、记录时间更新，游戏次数 + 1
     *      5、表t_score_details添加一条记录
     * @Date 2019/2/18 22:53
     * @Param [name, score]
     * @return void
     **/
    void saveScore(String name, int score);

    /**
     * @Author chasel
     * @Description 到达面膜格子
     *      1、获取字段mask看是否第一次中奖面膜
     *          是：改变mask的值，生成面膜二维码保存、数据库存储二维码地址
     *          否：提示信息
     * @Date 2019/2/18 22:58
     * @Param [name]
     * @return void
     **/
    void maskLattice(String name);

    /**
     * @Author chasel
     * @Description 到达问号格子
     *      1、抽奖： 中奖true，非中奖false。中奖只有珍爱套盒
     *      2、校验is_treasure_box字段的值
     *          值为0：可获得奖品，改变is_treasure_box值为1
     *          值为1：直接返回false，不中奖
     *      3、随机数50000个，只有返回0为中奖，其它非中奖。
     * @Date 2019/2/18 23:03
     * @Param [name]
     * @return void
     **/
    void questionLattice(String name);

    /**
     * @Author chasel
     * @Description 用户排名
     *      1、全国用户排名信息根据总分倒序、次数升序排序
     *      2、前端定时每小时准点调用该接口
     *      3、该用户当前排名、累计分数
     * @Date 2019/2/18 23:19
     * @Param [name]
     * @return java.util.List
     **/
    List userRank(String name);

    /**
     * @Author chasel
     * @Description 用户分数详情
     *      1、每次玩的日期和总分
     * @Date 2019/2/18 23:21
     * @Param [name]
     * @return java.util.List
     **/
    List userScoreDetails(String name);
}
