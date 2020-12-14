package cn.sabercon.main.ctrl;

import cn.sabercon.common.anno.CustomController;
import cn.sabercon.main.domain.model.LoginUserInfo;
import cn.sabercon.main.domain.model.UserInfo;
import cn.sabercon.main.domain.param.LoginParam;
import cn.sabercon.main.domain.param.UpdateUserParam;
import cn.sabercon.main.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import static cn.sabercon.common.util.StrUtils.maskPhoneNumber;

/**
 * @author SaberCon
 * @since 1.0.0
 */
@Api(tags = "用户相关接口")
@CustomController("user")
@RequiredArgsConstructor
public class UserCtrl {

    private final UserService service;

    @GetMapping("current")
    @ApiOperation("获取当前登录用户的信息")
    public LoginUserInfo getLoginInfo() {
        var info = service.getLoginInfo();
        info.setPhone(maskPhoneNumber(info.getPhone()));
        return info;
    }

    @PostMapping("login")
    @ApiOperation("用户登录(未注册会直接注册), 返回 token")
    public String login(@Valid LoginParam param) {
        return service.login(param);
    }

    @PutMapping("phone")
    @ApiOperation("换绑手机")
    public void updatePhone(@RequestParam String newPhone, @RequestParam String unbindCode, @RequestParam String bindCode) {
        service.updatePhone(newPhone, unbindCode, bindCode);
    }

    @PutMapping("pwd")
    @ApiOperation("修改密码")
    public void updatePwd(@RequestParam String newPwd, @RequestParam String code) {
        service.updatePwd(newPwd, code);
    }

    @PutMapping
    @ApiOperation("修改信息")
    public void update(@Valid UpdateUserParam param) {
        service.update(param);
    }

    @GetMapping
    @ApiOperation("获取用户信息")
    public UserInfo getInfo(@NotNull Long id) {
        return service.getInfo(id);
    }
}
