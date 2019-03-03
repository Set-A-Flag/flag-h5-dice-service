package com.chasel.h5.dice.service;

import com.chasel.h5.dice.exception.ServiceException;
import com.chasel.h5.dice.vo.OwnerVO;
import com.chasel.h5.dice.vo.UserVO;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface IH5DiceService {

    /**
     * @Author chasel
     * @Description 根据微信账号识别是用户还是店主
     *      1、查询is_owner字段，0不是、1是
     * @Date 2019/2/19 21:03
     * @Param [name]
     * @return int
     **/
    int checkUserOrOwner(String account);

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
    void registerStoreOwner(OwnerVO ownerVO);

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
    boolean checkNumOfGames(String account) throws ServiceException;

    /**
     * @Author chasel
     * @Description 保存用户游戏分数
     *      1、为了严谨，调用checkNumOfGames方法校验
     *      2、23:00~7:00时间段内或者活动结束时间后，直接return，不做以下操作
     *      3、获取总分total，然后和score相加保存
     *      4、记录时间更新，游戏次数 + 1
     *      5、表t_score_details添加一条记录
     * @Date 2019/2/18 22:53
     * @Param [name, score]
     * @return void
     **/
    void saveScore(String account, int score);

    /**
     * @Author chasel
     * @Description 到达面膜格子
     *      1、获取字段mask看是否第一次中奖面膜
     *          是true：改变mask的值，生成面膜二维码保存、数据库存储二维码地址
     *          否false：提示信息
     * @Date 2019/2/18 22:58
     * @Param [name]
     * @return void
     **/
    boolean maskLattice(String account);

    /**
     * @Author chasel
     * @Description 到达问号格子
     *      1、抽奖： 中奖true，非中奖false。中奖只有珍爱套盒
     *      2、校验is_treasure_box字段的值
     *          值为0：可获得奖品，改变is_treasure_box值为1，并且记录treasure_box_time时间值
     *          值为1：直接返回false，不中奖
     *      3、随机数50000个，只有返回0为中奖，其它非中奖。
     * @Date 2019/2/18 23:03
     * @Param [name]
     * @return void
     **/
    boolean questionLattice(String account);

    /**
     * @Author chasel
     * @Description 用户排名
     *      1、全国用户排名信息根据总分倒序、次数升序
     *      2、前端定时每小时准点调用该接口
     * @Date 2019/2/18 23:19
     * @Param
     * @return java.util.List
     **/
    List userRank();

    /**
     * @Author chasel
     * @Description 用户分数详情
     *      1、每次玩的日期和总分
     * @Date 2019/2/18 23:21
     * @Param [name]
     * @return java.util.List
     **/
    List userScoreDetails(String account);
    
    /**
     * @Author chasel
     * @Description 增加店主转发数
     *      1、活动是否结束，结束直接return
     *      2、查看表t_forward用户和店主关联关系是否存在，存在即return，不加1
     *      3、店主转发数+1：即字段total + 1
     *      4、记录时间更新
     * @Date 2019/2/19 20:52
     * @Param [name, score]
     * @return void
     **/
    void saveForwardingNum(String account);

    /**
     * @Author chasel
     * @Description 全国店主排名
     *       1、全国用户排名信息根据转发数倒序、时间升序
     *       2、前端定时每小时准点调用该接口
     * @Date 2019/2/19 20:57
     * @Param [account]
     * @return java.util.List
     **/
    List ownerRank();

    /**
     * @Author chasel
     * @Description 是否第一次查看奖品，true是，false否
     *      1、查询字段is_first_view，值为1 -> true 然后改变值为0, 值为0 -> false
     * @Date 2019/2/19 20:59
     * @Param [account]
     * @return boolean
     **/
    boolean isFirstViewPrizes(String account);

    /**
     * @Author chasel
     * @Description 第一次查看奖品，保存号码接口
     * @Date 2019/2/19 21:02
     * @Param [account, phone]
     * @return void
     **/
    void saveUserPhone(String account, String phone);

    /**
     * @Author chasel
     * @Description 查看中奖奖品类型接口
     *      1、查询字段is_treasure_box和mask
     *      2、构造成Map返回，字段值为0代表有奖品，为1不中奖
     * @Date 2019/2/19 21:08
     * @Param [account]
     * @return void
     **/
    Map<String, Integer> queryPrizes(String account);

    /**
     * @Author chasel
     * @Description 获取二维码接口
     * @Date 2019/2/19 21:06
     * @Param [account]
     * @return void
     **/
    void qrCode(String account);
    
    /**
     * @Author chasel
     * @Description 保存收货信息
     *      1、如果是活动结束后，抛出异常，返回提示语
     *      2、查询中奖时间treasure_box_time，如果超过24小时，直接提示
     * @Date 2019/2/19 21:28
     * @Param [userVO]
     * @return void
     **/
    void saveUserDeliveryInfo(UserVO userVO) throws ServiceException;

    /**
     * @Author chasel
     * @Description 检查是否中奖超过24小时，超过返回true，不超过返回false，中奖时间treasure_box_time
     * @Date 2019/2/19 21:32
     * @Param [account]
     * @return void
     **/
    boolean checkPrizesTimes(String account);

    /**
     * @Author chasel
     * @Description 是否获得面膜套装
     *      1、0-999范围的1000个随机数，0是中奖返回true，其它返回false
     * @Date 2019/2/19 21:48
     * @Param [account]
     * @return boolean
     **/
    boolean isMaskSet(String account);

    /**
     * @Author chasel
     * @Description 查询用户收货信息接口
     * @Date 2019/3/3 23:25
     * @Param [account]
     * @return java.lang.Object
     **/
    UserVO queryUserDeliveryInfo(String account);
}
