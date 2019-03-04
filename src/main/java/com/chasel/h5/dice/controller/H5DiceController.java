package com.chasel.h5.dice.controller;

import com.chasel.h5.dice.service.IH5DiceService;
import com.chasel.h5.dice.vo.OwnerVO;
import com.chasel.h5.dice.vo.ResponseResult;
import com.chasel.h5.dice.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.chasel.h5.dice.constant.Constants.*;

/**
 * @Author chasel
 * @Description TODO
 **/
@Api(value = "H5Dice", description = "H5Dice")
@RequestMapping("/dice")
@RestController
public class H5DiceController extends BaseController{

    @Autowired
    IH5DiceService h5DiceService;

    @ApiOperation(value = "根据微信账号识别是用户还是店主")
    @GetMapping("/checkUserOrOwner/{account}")
    public ResponseResult checkUserOrOwner(
            @ApiParam(required = true, name = "account", type = "path", value = "微信ID") @PathVariable  String account) {

        return value( () -> h5DiceService.checkUserOrOwner(account), QUERY_SUCCESS, QUERY_FAIL);
    }

    @ApiOperation(value = "店主报名接口")
    @PostMapping("/registerStoreOwner")
    public ResponseResult registerStoreOwner(
            @ApiParam(required = true, name = "ownerVO", type = "body", value = "店主信息") @RequestBody OwnerVO ownerVO) {
        return process(() -> h5DiceService.registerStoreOwner(ownerVO), ADD_SUCCESS, ADD_FAIL);
    }

    @ApiOperation(value = "检查是否有游戏次数：true是，false否")
    @GetMapping("/checkNumOfGames/{account}")
    public ResponseResult checkNumOfGames(
            @ApiParam(required = true, name = "account", type = "path", value = "微信ID") @PathVariable String account) {
        return value(() -> h5DiceService.checkNumOfGames(account), QUERY_SUCCESS, QUERY_FAIL);
    }

    @ApiOperation(value = "保存用户游戏分数")
    @PostMapping("/saveScore/{account}/{score}")
    public ResponseResult saveScore(
            @ApiParam(required = true, name = "account", type = "path", value = "微信ID") @PathVariable String account,
            @ApiParam(required = true, name = "score", type = "path", value = "游戏分数") @PathVariable int score) {
        return process(() -> h5DiceService.saveScore(account, score), ADD_SUCCESS, ADD_FAIL);
    }

    @ApiOperation(value = "到达面膜格子,true第一次中奖面膜")
    @GetMapping("/maskLattice/{account}")
    public ResponseResult maskLattice(
            @ApiParam(required = true, name = "account", type = "path", value = "微信ID") @PathVariable String account) {
        return value(() -> h5DiceService.maskLattice(account), QUERY_SUCCESS, QUERY_FAIL);
    }

    @ApiOperation(value = "到达问号格子,true中珍爱套盒")
    @GetMapping("/questionLattice/{account}")
    public ResponseResult questionLattice(
            @ApiParam(required = true, name = "account", type = "path", value = "微信ID") @PathVariable String account) {
        return value(() -> h5DiceService.questionLattice(account), QUERY_SUCCESS, QUERY_FAIL);
    }

    @ApiOperation(value = "全国用户排名")
    @GetMapping("/userRank")
    public ResponseResult userRank() {

        return value(h5DiceService::userRank, QUERY_SUCCESS, QUERY_FAIL);
    }

    @ApiOperation(value = "用户分数详情")
    @GetMapping("/userScoreDetails/{account}")
    public ResponseResult userScoreDetails(
            @ApiParam(required = true, name = "account", type = "path", value = "微信ID") @PathVariable String account) {

        return value(() -> h5DiceService.userScoreDetails(account), QUERY_SUCCESS, QUERY_FAIL);
    }

    @ApiOperation(value = "增加店主转发数")
    @GetMapping("/saveForwardingNum/{account}")
    public ResponseResult saveForwardingNum(
            @ApiParam(required = true, name = "account", type = "path", value = "微信ID") @PathVariable String account) {

        return process(() -> h5DiceService.saveForwardingNum(account), ADD_SUCCESS, ADD_FAIL);
    }

    @ApiOperation(value = "全国店主排名")
    @GetMapping("/ownerRank")
    public ResponseResult ownerRank() {

        return value(h5DiceService::ownerRank, QUERY_SUCCESS, QUERY_FAIL);
    }

    @ApiOperation(value = "是否第一次查看奖品，true是，false否")
    @GetMapping("/isFirstViewPrizes/{account}")
    public ResponseResult isFirstViewPrizes(
            @ApiParam(required = true, name = "account", type = "path", value = "微信ID") @PathVariable String account) {

        return value(() -> h5DiceService.isFirstViewPrizes(account), QUERY_SUCCESS, QUERY_FAIL);
    }

    @ApiOperation(value = "第一次查看奖品，保存号码接口")
    @PostMapping("/saveUserPhone/{account}/{phone}")
    public ResponseResult saveUserPhone(
            @ApiParam(required = true, name = "account", type = "path", value = "微信ID") @PathVariable String account,
            @ApiParam(required = true, name = "phone", type = "path", value = "电话号码") @PathVariable String phone) {

        return process(() -> h5DiceService.saveUserPhone(account, phone), ADD_SUCCESS, ADD_FAIL);
    }

    @ApiOperation(value = "查看中奖奖品类型接口:珍爱套盒isTreasureBox,面膜mask -> 1不中奖，0中奖")
    @GetMapping("/queryPrizes/{account}")
    public ResponseResult queryPrizes(
            @ApiParam(required = true, name = "account", type = "path", value = "微信ID") @PathVariable String account) {
        return value(() -> h5DiceService.queryPrizes(account), QUERY_SUCCESS, QUERY_FAIL);
    }

    @ApiOperation(value = "获取二维码图片接口")
    @GetMapping("/qrCode/{account}")
    public ResponseResult qrCode(
            @ApiParam(required = true, name = "account", type = "path", value = "微信ID") @PathVariable String account) {

        return process(() -> h5DiceService.qrCode(account), QUERY_SUCCESS, QUERY_FAIL);
    }

    @ApiOperation(value = "保存收货信息")
    @PostMapping("/saveUserDeliveryInfo")
    public ResponseResult saveUserDeliveryInfo(
            @ApiParam(required = true, name = "userVO", type = "body", value = "收货信息") @RequestBody UserVO userVO) {

        return process(() -> h5DiceService.saveUserDeliveryInfo(userVO), ADD_SUCCESS, ADD_FAIL);
    }

    @ApiOperation(value = "查询收货信息")
    @GetMapping("/queryUserDeliveryInfo/{account}")
    public ResponseResult queryUserDeliveryInfo(
            @ApiParam(required = true, name = "account", type = "path", value = "微信ID") @PathVariable String account) {

        return value(() -> h5DiceService.queryUserDeliveryInfo(account), QUERY_SUCCESS, QUERY_FAIL);
    }

    @ApiOperation(value = "校验中奖时间是否超过24小时,超过返回true，不超过返回false")
    @GetMapping("/checkPrizesTimes/{account}")
    public ResponseResult checkPrizesTimes(
            @ApiParam(required = true, name = "account", type = "path", value = "微信ID") @PathVariable String account) {

        return value(() -> h5DiceService.checkPrizesTimes(account), QUERY_SUCCESS, QUERY_FAIL);
    }

    @ApiOperation(value = "抽奖：是否获得面膜套装，true获得")
    @GetMapping("/isMaskSet/{account}")
    public ResponseResult isMaskSet(
            @ApiParam(required = true, name = "account", type = "path", value = "微信ID") @PathVariable String account) {
        return value(() -> h5DiceService.isMaskSet(account), QUERY_SUCCESS, QUERY_FAIL);
    }

    @ApiOperation(value = "核销列表")
    @GetMapping("/writeOff")
    public ResponseResult writeOff() {
        return value(h5DiceService::writeOff, QUERY_SUCCESS, QUERY_FAIL);
    }
}
