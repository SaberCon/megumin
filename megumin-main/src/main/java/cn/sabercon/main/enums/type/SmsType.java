package cn.sabercon.main.enums.type;

import cn.sabercon.common.enums.IntEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

/**
 * 短信验证码类型
 *
 * @author SaberCon
 * @since 1.0.0
 */
@Getter
@AllArgsConstructor
@Accessors(fluent = true)
public enum SmsType implements IntEnum {

    LOGIN(1, "登录注册"),
    BIND_PHONE(2, "绑定手机"),
    UNBIND_PHONE(3, "解绑手机"),
    UPDATE_PWD(4, "修改密码"),
    ;

    private final int val;
    private final String desc;
}
