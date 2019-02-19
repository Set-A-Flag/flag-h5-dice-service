package com.chasel.h5.dice.service.impl;

import com.chasel.h5.dice.service.IH5DiceService;
import com.chasel.h5.dice.vo.OwnerVO;
import com.chasel.h5.dice.vo.UserVO;
import org.springframework.stereotype.Service;

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

    @Override
    public int checkUserOrOwner(String account) {
        return 0;
    }

    @Override
    public void registerStoreOwner(OwnerVO ownerVO) {

    }

    @Override
    public boolean checkNumOfGames(String account) {
        return false;
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
