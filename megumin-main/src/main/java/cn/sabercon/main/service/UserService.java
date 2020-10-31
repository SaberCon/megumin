package cn.sabercon.main.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import cn.sabercon.common.data.RedisHelper;
import cn.sabercon.common.enums.type.Gender;
import cn.sabercon.common.json.Json;
import cn.sabercon.common.util.Assert;
import cn.sabercon.common.util.HttpUtils;
import cn.sabercon.common.util.Jwt;
import cn.sabercon.main.domain.dto.UserInfo;
import cn.sabercon.main.domain.entity.User;
import cn.sabercon.main.domain.param.LoginParam;
import cn.sabercon.main.enums.type.SmsType;
import cn.sabercon.main.manager.SmsManager;
import cn.sabercon.main.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;

import static cn.sabercon.common.util.StrUtils.buildRedisKey;
import static cn.sabercon.main.constant.RedisConstant.LOGIN_USER_PREFIX;
import static cn.sabercon.main.enums.code.MainCode.LOGIN_ERROR;
import static cn.sabercon.main.enums.code.MainCode.SMS_CODE_WRONG;

/**
 * @author SaberCon
 * @since 1.0.0
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo repo;

    private final RedisHelper redisHelper;

    private final SmsManager smsManager;

    private static final String DEFAULT_AVATAR = "http://oss.sabercon.cn/base/takagi.jpg";

    public UserInfo getLoginUserInfo() {
        var userId = HttpUtils.getUserIdOrError();
        return redisHelper.get(buildRedisKey(LOGIN_USER_PREFIX, userId), UserInfo.class);
    }

    public String login(LoginParam param) {
        User user;
        if (StringUtils.isEmpty(param.getCode())) {
            // 密码登录
            user = repo.findByPhone(param.getPhone()).orElseThrow(LOGIN_ERROR::exception);
            Assert.isTrue(Objects.equals(user.getPassword(), SecureUtil.md5(param.getPassword())), LOGIN_ERROR);
        } else {
            // 验证码登录
            Assert.isTrue(smsManager.checkCode(SmsType.LOGIN, param.getPhone(), param.getCode()), SMS_CODE_WRONG);
            user = repo.findByPhone(param.getPhone()).orElseGet(() -> register(param.getPhone()));
        }
        var userInfo = Json.convert(user, UserInfo.class);
        redisHelper.set(buildRedisKey(LOGIN_USER_PREFIX, user.getId()), userInfo);
        return Jwt.generateTokenById(user.getId(), DateUtil.nextMonth());
    }

    private User register(String phone) {
        var user = new User();
        user.setPhone(phone);
        user.setUsername(generateUsername());
        user.setAvatar(DEFAULT_AVATAR);
        user.setGender(Gender.UNKNOWN);
        repo.save(user);
        return user;
    }

    private String generateUsername() {
        String username;
        do {
            username = "user" + IdUtil.simpleUUID();
        } while (repo.existsByUsername(username));
        return username;
    }
}
