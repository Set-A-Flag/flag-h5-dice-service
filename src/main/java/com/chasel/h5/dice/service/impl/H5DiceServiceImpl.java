package com.chasel.h5.dice.service.impl;

import com.chasel.h5.dice.dao.IH5DiceDao;
import com.chasel.h5.dice.exception.ServiceException;
import com.chasel.h5.dice.service.IH5DiceService;
import com.chasel.h5.dice.vo.OwnerVO;
import com.chasel.h5.dice.vo.UserVO;
import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Pattern;

/**
 * @Author chasel
 * @Description TODO
 * @Date
 * @return
 **/
@Service
public class H5DiceServiceImpl implements IH5DiceService {

    private static final Logger log = LoggerFactory.getLogger(H5DiceServiceImpl.class);
    private static final String PHONE_NUMBER_REG = "^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\\d{8}$";
    public static final String ACCOUNT_NULL = "账号错误";
    public static final String NUM_OF_GAMES = "numOfGames";
    public static final String RECORDING_TIME = "recordingTime";
    public static final String YYYY_MM_DD_HH = "yyyy-MM-dd HH";
    public static final String MSG_NO_NUM_OF_GAMES = "请下个小时再来玩";
    public static final String PHONE_ERROR = "手机号码错误";
    public static final String EXIST_PHONE = "已经存在电话号码!";
    @Autowired
    IH5DiceDao ih5DiceDao;

    @Override
    public int checkUserOrOwner(String account) {
        return 0;
    }

    @Override
    public void registerStoreOwner(OwnerVO ownerVO) {

    }

    @Override
    public boolean checkNumOfGames(String account) throws ServiceException {
        // 1、获取数据库字段recording_time记录时间
        Map<String, Object> map = ih5DiceDao.getRecordingTimeByAccount(account);
        if (map == null) return true;
        Integer numOfGames = MapUtils.getInteger(map, NUM_OF_GAMES);
        Timestamp time = (Timestamp) map.get(RECORDING_TIME);
        // 2、与当前时间小时比较
        String timeStr = time.toString();
        String timeHour = timeStr.substring(0, timeStr.indexOf(":"));
        SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD_HH);
        String currentTime = sdf.format(new Date());
        // 2.1 相等 -> 则判断number_of_games游戏次数是否等于3：是(提示：请下个小时再来玩),否true
        if (timeHour.equalsIgnoreCase(currentTime)) {
            if (numOfGames.equals(3)) throw new ServiceException(MSG_NO_NUM_OF_GAMES);
            else return true;
        } else { // 2.2 不等 -> 则有3次机会
            return  true;
        }
    }

    @Override
    public void saveScore(String account, int score) {

    }

    @Override
    public boolean maskLattice(String account) {
        //1、获取字段mask看是否第一次中奖面膜
        Integer mask = ih5DiceDao.maskLattice(account);
        //  否false：提示信息
        if (mask != null && mask.equals(1)) {
            // TODO  是true：改变mask的值，生成面膜二维码保存、数据库存储二维码地址
            return true;
        } else{
            return false;
        }
    }

    @Override
    public boolean questionLattice(String account) {
        //1、抽奖： 中奖true，非中奖false。中奖只有珍爱套盒
        //2、校验is_treasure_box字段的值
        Integer isTreasureBox = ih5DiceDao.questionLattice(account);
        //   值为0：可获得奖品，改变is_treasure_box值为1，并且记录treasure_box_time时间值
        if (isTreasureBox != null && isTreasureBox.equals(0)) {
            // 2.1、随机数50000个，只有返回0为中奖，其它非中奖。
            Integer code = new Random().nextInt(49999);
            if (code.equals(0)) {
                ih5DiceDao.changeIsTreasureBox(account, 1, new Timestamp(System.currentTimeMillis()));
                return true;
            }else{
                return false;
            }
        }else { //值为1：直接返回false，不中奖
            return false;
        }
    }

    @Override
    public List userRank() {
        return null;
    }

    @Override
    public List userScoreDetails(String account) {
        return null;
    }

    @Override
    public void saveForwardingNum(String account) {

    }

    @Override
    public List ownerRank() {
        return null;
    }

    @Override
    public boolean isFirstViewPrizes(String account) {
        // 1、查询字段is_first_view，值为1 -> true 然后改变值为0, 值为0 -> false
        Integer code = ih5DiceDao.isFirstViewPrizes(account);
        if (code != null && code.equals(1)) {
            return false;
        }else {
            return true;
        }
    }

    @Override
    public void saveUserPhone(String account, String phone) {
        Assert.isTrue(!StringUtils.isEmpty(account), ACCOUNT_NULL);
        boolean matches = Pattern.matches(PHONE_NUMBER_REG, phone);
        Assert.isTrue(matches, PHONE_ERROR);
        String phonev = ih5DiceDao.queryPhone(account);
        Assert.isTrue(StringUtils.isEmpty(phonev), EXIST_PHONE);
        ih5DiceDao.saveUserPhone(account, phone);
    }

    @Override
    public Map<String, Integer> queryPrizes(String account) {
        //1、查询字段is_treasure_box和mask
        Map<String, Integer> resultMap = ih5DiceDao.queryPrizes(account);
        //2、构造成Map返回，字段值为0代表有奖品，为1不中奖
        return resultMap;
    }

    @Override
    public void qrCode(String account) {

    }

    @Override
    public void saveUserDeliveryInfo(UserVO userVO) {

    }

    @Override
    public boolean checkPrizesTimes(String account) {
        // 检查是否中奖超过24小时，超过返回true，不超过返回false，中奖时间treasure_box_time
        Timestamp time = ih5DiceDao.getTreasureBoxTimeBy(account);
        if (time != null) {
            long diff  = System.currentTimeMillis() - time.getTime();
            long days = diff / (1000 * 60 * 60 * 24);
            if (days >= 1) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isMaskSet(String account) {
        //1、0-999范围的1000个随机数，0是中奖返回true，其它返回false
        Integer code = new Random().nextInt(99);
        log.info("0~99 -> account : {} , code ： {}", account, code);
        if (code.equals(0)){
            return true;
        } else {
            return false;
        }
    }
}
