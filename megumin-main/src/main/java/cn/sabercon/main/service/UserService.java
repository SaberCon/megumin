package cn.sabercon.main.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.SecureUtil;
import cn.sabercon.common.anno.Tx;
import cn.sabercon.common.data.RedisHelper;
import cn.sabercon.common.enums.type.Gender;
import cn.sabercon.common.util.Checker;
import cn.sabercon.common.util.HttpUtils;
import cn.sabercon.common.util.Jwt;
import cn.sabercon.common.util.PojoUtils;
import cn.sabercon.main.component.SmsManager;
import cn.sabercon.main.domain.dto.UserSimpleInfo;
import cn.sabercon.main.domain.entity.User;
import cn.sabercon.main.domain.model.LoginUserInfo;
import cn.sabercon.main.domain.model.UserInfo;
import cn.sabercon.main.domain.param.LoginParam;
import cn.sabercon.main.domain.param.UpdateUserParam;
import cn.sabercon.main.enums.type.SmsType;
import cn.sabercon.main.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

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
    private final SmsManager smsManager;

    public LoginUserInfo getLoginInfo() {
        return redisHelper.get(buildRedisKey(LOGIN_USER_PREFIX, HttpUtils.userId()), LoginUserInfo.class);
    }

    @Tx
    public String login(LoginParam param) {
        User user;
        if (ObjectUtils.isEmpty(param.getCode())) {
            // 密码登录
            user = repo.findByPhone(param.getPhone()).orElseThrow(LOGIN_ERROR::exception);
            Checker.isTrue(Objects.equals(user.getPassword(), SecureUtil.md5(param.getPassword())), LOGIN_ERROR);
        } else {
            // 验证码登录
            Checker.isTrue(smsManager.checkCode(SmsType.LOGIN, param.getPhone(), param.getCode()), SMS_CODE_WRONG);
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
        user.setGender(Gender.UNKNOWN);
        user.setKarma(0L);
        user.setUp(0L);
        user.setDown(0L);
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
        Checker.isTrue(smsManager.checkCode(SmsType.UNBIND_PHONE, oldPhone, unbindCode), SMS_CODE_WRONG);
        Checker.isTrue(smsManager.checkCode(SmsType.BIND_PHONE, newPhone, bindCode), SMS_CODE_WRONG);
        Checker.isTrue(Objects.equals(oldPhone, newPhone) || !repo.existsByPhone(newPhone), PHONE_ALREADY_BOUND);
        var user = repo.findById(HttpUtils.userId()).orElseThrow();
        user.setPhone(newPhone);
        refreshUserInfoCache(user);
    }

    @Tx
    public void updatePwd(String newPwd, String code) {
        Checker.isTrue(smsManager.checkCode(SmsType.UPDATE_PWD, getLoginInfo().getPhone(), code), SMS_CODE_WRONG);
        var user = repo.findById(HttpUtils.userId()).orElseThrow();
        user.setPassword(SecureUtil.md5(newPwd));
    }

    @Tx
    public void update(UpdateUserParam param) {
        var user = repo.findById(HttpUtils.userId()).orElseThrow();
        Checker.isTrue(Objects.equals(user.getUsername(), param.getUsername()) || !repo.existsByUsername(param.getUsername()), USERNAME_EXISTS);
        BeanUtil.copyProperties(param, user, CopyOptions.create().ignoreNullValue());
        refreshUserInfoCache(user);
    }

    private void refreshUserInfoCache(User user) {
        var userInfo = PojoUtils.convert(user, LoginUserInfo.class);
        redisHelper.set(buildRedisKey(LOGIN_USER_PREFIX, user.getId()), userInfo, 30, TimeUnit.DAYS);
    }

    public UserInfo getInfo(Long id) {
        return repo.findById(id).map(e -> PojoUtils.convert(e, UserInfo.class)).orElse(null);
    }

    public UserSimpleInfo getSimpleInfo(Long id) {
        return repo.findById(id).map(e -> PojoUtils.convert(e, UserSimpleInfo.class)).orElse(null);
    }
}
