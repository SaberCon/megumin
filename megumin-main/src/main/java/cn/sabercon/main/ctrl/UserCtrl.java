package cn.sabercon.main.ctrl;

import cn.sabercon.common.anno.ServiceController;
import cn.sabercon.main.domain.param.LoginParam;
import cn.sabercon.main.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * @author SaberCon
 * @since 1.0.0
 */
@Api(tags = "用户相关接口")
@ServiceController("user")
@RequiredArgsConstructor
public class UserCtrl {

    private final UserService service;

    @PostMapping("login")
    @ApiOperation("用户登录(未注册会直接注册), 返回 token")
    public String login(@RequestBody @Valid LoginParam param) {
        return service.login(param);
    }
}
