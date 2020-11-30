package cn.sabercon.main.ctrl;

import cn.sabercon.common.anno.CommonController;
import cn.sabercon.main.enums.type.SmsType;
import cn.sabercon.main.manager.SmsManager;
import cn.sabercon.main.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;

import javax.validation.constraints.NotNull;

/**
 * @author SaberCon
 * @since 1.0.0
 */
@Api(tags = "短信相关接口")
@CommonController("sms")
@RequiredArgsConstructor
public class SmsCtrl {

    private final SmsManager manager;

    private final UserService userService;

    @GetMapping
    @ApiOperation("发送短信验证码")
    public void sendCode(@NotNull SmsType type, @ApiParam("为空时表示当前登录用户的手机号") String phone) {
        if (StringUtils.isEmpty(phone)) {
            phone = userService.getLoginInfo().getPhone();
        }
        manager.sendCode(type, phone);
    }

    @GetMapping("check")
    @ApiOperation("校验短信验证码是否正确")
    public boolean checkCode(@NotNull SmsType type, @ApiParam("为空时表示当前登录用户的手机号") String phone, @NotNull String code) {
        if (StringUtils.isEmpty(phone)) {
            phone = userService.getLoginInfo().getPhone();
        }
        return manager.checkCode(type, phone, code);
    }
}
