package cn.sabercon.main.ctrl;

import cn.sabercon.common.anno.ServiceController;
import cn.sabercon.main.domain.dto.UserInfo;
import cn.sabercon.main.domain.param.LoginParam;
import cn.sabercon.main.domain.param.UpdatePhoneParam;
import cn.sabercon.main.domain.param.UpdatePwdParam;
import cn.sabercon.main.domain.param.UpdateUserParam;
import cn.sabercon.main.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

import static cn.sabercon.common.util.StrUtils.maskPhoneNumber;

/**
 * @author SaberCon
 * @since 1.0.0
 */
@Api(tags = "用户相关接口")
@ServiceController("user")
@RequiredArgsConstructor
public class UserCtrl {

    private final UserService service;

    @GetMapping
    @ApiOperation("获取当前登录用户的信息")
    public UserInfo getLoginUserInfo() {
        UserInfo info = service.getLoginUserInfo();
        info.setPhone(maskPhoneNumber(info.getPhone()));
        return info;
    }

    @PostMapping("login")
    @ApiOperation("用户登录(未注册会直接注册), 返回 token")
    public String login(@RequestBody @Valid LoginParam param) {
        return service.login(param);
    }

    @PutMapping("phone")
    @ApiOperation("换绑手机")
    public void updatePhone(@RequestBody @Valid UpdatePhoneParam param) {
        service.updatePhone(param);
    }

    @PutMapping("pwd")
    @ApiOperation("修改密码")
    public void updatePwd(@RequestBody @Valid UpdatePwdParam param) {
        service.updatePwd(param);
    }

    @PutMapping
    @ApiOperation("修改信息")
    public void update(@RequestBody @Valid UpdateUserParam param) {
        service.update(param);
    }
}
