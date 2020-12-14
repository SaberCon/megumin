package cn.sabercon.main.enums.code;

import cn.sabercon.common.enums.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

/**
 * 状态码
 *
 * @author SaberCon
 * @since 1.0.0
 */
@Getter
@AllArgsConstructor
@Accessors(fluent = true)
public enum MainCode implements ErrorCode {

    SMS_CODE_WRONG("B0101", "the sms code is wrong"),
    LOGIN_ERROR("B0102", "the phone or password is wrong"),
    PHONE_ALREADY_BOUND("B0103", "the phone has been bound to other account"),
    USERNAME_EXISTS("B0104", "the username has been picked by other account"),
    ;

    private final String code;
    private final String msg;
}
