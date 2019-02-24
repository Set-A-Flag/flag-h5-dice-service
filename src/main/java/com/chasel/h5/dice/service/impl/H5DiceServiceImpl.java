package com.chasel.h5.dice.service.impl;

import com.chasel.h5.dice.dao.IH5DiceDao;
import com.chasel.h5.dice.exception.ServiceException;
import com.chasel.h5.dice.service.IH5DiceService;
import com.chasel.h5.dice.vo.OwnerVO;
import com.chasel.h5.dice.vo.UserVO;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author chasel
 * @Description TODO
 * @Date
 * @return
 **/
@Service
public class H5DiceServiceImpl implements IH5DiceService {

    public static final String NUM_OF_GAMES = "numOfGames";
    public static final String RECORDING_TIME = "recordingTime";
    public static final String YYYY_MM_DD_HH = "yyyy-MM-dd HH";
    public static final String MSG_NO_NUM_OF_GAMES = "请下个小时再来玩";
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
        Integer numOfGames = MapUtils.getInteger(map, NUM_OF_GAMES);
        Timestamp time = (Timestamp) map.get(RECORDING_TIME);
        // 2、与当前时间小时比较
        if (time == null) return true;
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
        return false;
    }

    @Override
    public boolean questionLattice(String account) {
        return false;
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
        return false;
    }

    @Override
    public void saveUserPhone(String account, String phone) {

    }

    @Override
    public Map<String, Object> queryPrizes(String account) {
        return null;
    }

    @Override
    public void qrCode(String account) {

    }

    @Override
    public void saveUserDeliveryInfo(UserVO userVO) {

    }

    @Override
    public boolean checkPrizesTimes(String account) {

        return false;
    }

    @Override
    public boolean isMaskSet(String account) {
        return false;
    }
}
