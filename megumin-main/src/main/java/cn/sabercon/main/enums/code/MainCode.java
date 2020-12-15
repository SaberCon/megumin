package cn.sabercon.main.enums.code;

import cn.sabercon.common.enums.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

/**
 * 错误码
 *
 * @author SaberCon
 * @since 1.0.0
 */
@Getter
@AllArgsConstructor
@Accessors(fluent = true)
public enum MainCode implements ErrorCode {

    SMS_CODE_WRONG("10101", "the sms code is wrong"),
    LOGIN_ERROR("10102", "the phone or password is wrong"),
    PHONE_ALREADY_BOUND("10103", "the phone has been bound to other account"),
    USERNAME_EXISTS("10104", "the username has been picked by other account"),
    ;

    private final String code;
    private final String msg;
}
