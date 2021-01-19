package cn.sabercon.main.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.SecureUtil;
import cn.sabercon.common.anno.Tx;
import cn.sabercon.common.data.RedisHelper;
import cn.sabercon.common.util.Checker;
import cn.sabercon.common.util.HttpUtils;
import cn.sabercon.common.util.Jwt;
import cn.sabercon.common.util.PojoUtils;
import cn.sabercon.main.component.SmsHelper;
import cn.sabercon.main.domain.entity.User;
import cn.sabercon.main.domain.model.UserInfo;
import cn.sabercon.main.domain.param.LoginParam;
import cn.sabercon.main.domain.param.UpdateUserParam;
import cn.sabercon.main.enums.type.SmsType;
import cn.sabercon.main.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static cn.sabercon.common.util.StrUtils.buildRedisKey;
import static cn.sabercon.main.constant.RedisConstant.LOGIN_USER_PREFIX;
import static cn.sabercon.main.enums.code.MainCode.*;

/**
 * @author SaberCon
 * @since 1.0.0
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private static final String DEFAULT_AVATAR = "http://oss.sabercon.cn/base/takagi.jpg";
    private final UserRepo repo;
    private final RedisHelper redisHelper;
    private final SmsHelper smsHelper;

    public UserInfo getLoginInfo() {
        return redisHelper.get(buildRedisKey(LOGIN_USER_PREFIX, HttpUtils.userId()), UserInfo.class);
    }

    @Tx
    public String login(LoginParam param) {
        User user;
        if (param.getType() == LoginParam.Type.PWD) {
            // 密码登录
            user = repo.findByPhone(param.getPhone()).orElseThrow(LOGIN_ERROR::exception);
            Checker.isTrue(Objects.equals(user.getPassword(), SecureUtil.md5(param.getCode())), LOGIN_ERROR);
        } else {
            // 验证码登录
            Checker.isTrue(smsHelper.checkCode(SmsType.LOGIN, param.getPhone(), param.getCode()), SMS_CODE_WRONG);
            user = repo.findByPhone(param.getPhone()).orElseGet(() -> register(param.getPhone()));
        }
        refreshUserInfoCache(user);
        return Jwt.generateTokenById(user.getId(), DateUtil.nextMonth());
    }

    private User register(String phone) {
        var user = new User();
        user.setPhone(phone);
        user.setUsername(generateUsername());
        user.setAvatar(DEFAULT_AVATAR);
        repo.save(user);
        return user;
    }

    private String generateUsername() {
        // 防止随机名称重复
        String username;
        do {
            username = "user" + RandomUtil.randomNumbers(8);
        } while (repo.existsByUsername(username));
        return username;
    }

    @Tx
    public void updatePhone(String newPhone, String unbindCode, String bindCode) {
        var oldPhone = getLoginInfo().getPhone();
        Checker.isTrue(smsHelper.checkCode(SmsType.UNBIND_PHONE, oldPhone, unbindCode), SMS_CODE_WRONG);
        Checker.isTrue(smsHelper.checkCode(SmsType.BIND_PHONE, newPhone, bindCode), SMS_CODE_WRONG);
        Checker.isTrue(!repo.existsByPhone(newPhone), PHONE_ALREADY_BOUND);
        var user = repo.findById(HttpUtils.userId()).orElseThrow();
        user.setPhone(newPhone);
        refreshUserInfoCache(user);
    }

    @Tx
    public void updatePwd(String newPwd, String code) {
        Checker.isTrue(smsHelper.checkCode(SmsType.UPDATE_PWD, getLoginInfo().getPhone(), code), SMS_CODE_WRONG);
        var user = repo.findById(HttpUtils.userId()).orElseThrow();
        user.setPassword(SecureUtil.md5(newPwd));
        redisHelper.delete(buildRedisKey(LOGIN_USER_PREFIX, user.getId()));
    }

    @Tx
    public void update(UpdateUserParam param) {
        var user = repo.findById(HttpUtils.userId()).orElseThrow();
        Checker.isTrue(Objects.equals(user.getUsername(), param.getUsername()) || !repo.existsByUsername(param.getUsername()), USERNAME_EXISTS);
        PojoUtils.copy(param, user);
        refreshUserInfoCache(user);
    }

    private void refreshUserInfoCache(User user) {
        var userInfo = PojoUtils.convert(user, UserInfo.class);
        redisHelper.set(buildRedisKey(LOGIN_USER_PREFIX, user.getId()), userInfo, 30, TimeUnit.DAYS);
    }

    public UserInfo getInfo(Long id) {
        return repo.findById(id).map(e -> PojoUtils.convert(e, UserInfo.class)).orElse(null);
    }
}
