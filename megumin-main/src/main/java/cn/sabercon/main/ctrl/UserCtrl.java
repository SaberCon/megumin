package cn.sabercon.main.ctrl;

import cn.sabercon.common.anno.CustomController;
import cn.sabercon.main.domain.model.UserInfo;
import cn.sabercon.main.domain.param.LoginParam;
import cn.sabercon.main.domain.param.UpdateUserParam;
import cn.sabercon.main.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import static cn.sabercon.common.util.StrUtils.maskPhoneNumber;

/**
 * @author SaberCon
 * @since 1.0.0
 */
@CustomController("user")
@RequiredArgsConstructor
public class UserCtrl {

    private final UserService service;

    @GetMapping("current")
    public UserInfo getLoginInfo() {
        var info = service.getLoginInfo();
        info.setPhone(maskPhoneNumber(info.getPhone()));
        return info;
    }

    @PostMapping("login")
    public String login(@Valid LoginParam param) {
        return service.login(param);
    }

    @PutMapping("phone")
    public void updatePhone(@NotNull String newPhone, @NotNull String unbindCode, @NotNull String bindCode) {
        service.updatePhone(newPhone, unbindCode, bindCode);
    }

    @PutMapping("pwd")
    public void updatePwd(@NotNull String newPwd, @NotNull String code) {
        service.updatePwd(newPwd, code);
    }

    @PutMapping
    public void update(@Valid UpdateUserParam param) {
        service.update(param);
    }

    @GetMapping
    public UserInfo getInfo(@NotNull Long id) {
        return service.getInfo(id);
    }
}
