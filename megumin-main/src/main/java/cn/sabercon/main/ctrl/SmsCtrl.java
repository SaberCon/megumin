package cn.sabercon.main.ctrl;

import cn.sabercon.common.anno.ServiceController;
import cn.sabercon.main.enums.type.SmsType;
import cn.sabercon.main.manager.SmsManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

import javax.validation.constraints.NotNull;

/**
 * @author SaberCon
 * @since 1.0.0
 */
@Api(tags = "短信相关接口")
@ServiceController("sms")
@RequiredArgsConstructor
public class SmsCtrl {

    private final SmsManager manager;

    @GetMapping
    @ApiOperation("发送短信验证码")
    public void sendCode(@ApiParam("短信类型") @NotNull SmsType type, @ApiParam("手机号码") @NotNull String phone) {
        manager.sendCode(type, phone);
    }

    @GetMapping("check")
    @ApiOperation("校验短信验证码是否正确")
    public boolean checkCode(@ApiParam("短信类型") @NotNull SmsType type, @ApiParam("手机号码") @NotNull String phone, @ApiParam("验证码") @NotNull String code) {
        return manager.checkCode(type, phone, code);
    }
}
