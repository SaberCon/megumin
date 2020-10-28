package cn.sabercon.main.ctrl;

import cn.sabercon.common.anno.ServiceController;
import cn.sabercon.main.enums.type.SmsType;
import cn.sabercon.main.manager.SmsManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

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
    public void sendCode(@ApiParam("短信类型") SmsType type, @ApiParam("手机号码") String phone) {
        manager.sendCode(type, phone);
    }
}
