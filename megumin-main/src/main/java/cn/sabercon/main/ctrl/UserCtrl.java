package cn.sabercon.main.ctrl;

import cn.sabercon.common.anno.ServiceController;
import cn.sabercon.main.service.UserService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

/**
 * @author SaberCon
 * @since 1.0.0
 */
@Api(tags = "用户相关接口")
@ServiceController("user")
@RequiredArgsConstructor
public class UserCtrl {

    private final UserService service;
}
