package cn.sabercon.main.ctrl;

import cn.sabercon.common.anno.CustomController;
import cn.sabercon.main.component.SmsHelper;
import cn.sabercon.main.enums.type.SmsType;
import cn.sabercon.main.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

import javax.validation.constraints.NotNull;

/**
 * @author SaberCon
 * @since 1.0.0
 */
@CustomController("sms")
@RequiredArgsConstructor
public class SmsCtrl {

    private final SmsHelper manager;
    private final UserService userService;

    @GetMapping
    public void sendCode(@NotNull SmsType type, String phone) {
        if (type != SmsType.LOGIN) {
            phone = userService.getLoginInfo().getPhone();
        }
        manager.sendCode(type, phone);
    }

    @GetMapping("check")
    public boolean checkCode(@NotNull SmsType type, @NotNull String code) {
        return manager.checkCode(type, userService.getLoginInfo().getPhone(), code);
    }
}
