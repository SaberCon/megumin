package cn.sabercon.main.ctrl;

import cn.sabercon.common.anno.ServiceController;
import cn.sabercon.common.data.RedisHelper;
import cn.sabercon.common.util.HttpUtils;
import cn.sabercon.main.domain.entity.User;
import cn.sabercon.main.enums.type.SmsType;
import cn.sabercon.main.manager.SmsManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;

import javax.validation.constraints.NotNull;

import static cn.sabercon.common.util.StrUtils.buildRedisKey;
import static cn.sabercon.main.constant.RedisConstant.LOGIN_USER_PREFIX;

/**
 * @author SaberCon
 * @since 1.0.0
 */
@Api(tags = "短信相关接口")
@ServiceController("sms")
@RequiredArgsConstructor
public class SmsCtrl {

    private final SmsManager manager;

    private final RedisHelper redisHelper;

    @GetMapping
    @ApiOperation("发送短信验证码")
    public void sendCode(@ApiParam("短信类型") @NotNull SmsType type, @ApiParam("手机号码, 为空时表示当前登录用户的手机号") String phone) {
        if (StringUtils.isEmpty(phone)) {
            phone = getLoginUserPhone();
        }
        manager.sendCode(type, phone);
    }

    @GetMapping("check")
    @ApiOperation("校验短信验证码是否正确")
    public boolean checkCode(@ApiParam("短信类型") @NotNull SmsType type, @ApiParam("手机号码, 为空时表示当前登录用户的手机号") String phone, @ApiParam("验证码") @NotNull String code) {
        if (StringUtils.isEmpty(phone)) {
            phone = getLoginUserPhone();
        }
        return manager.checkCode(type, phone, code);
    }

    private String getLoginUserPhone() {
        Long userId = HttpUtils.getUserIdOrError();
        User user = redisHelper.get(buildRedisKey(LOGIN_USER_PREFIX, userId), User.class);
        return user.getPhone();
    }
}
