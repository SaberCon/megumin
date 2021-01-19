package cn.sabercon.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

/**
 * 常用的基础类错误码
 *
 * @author SaberCon
 * @since 1.0.0
 */
@Getter
@AllArgsConstructor
@Accessors(fluent = true)
public enum BaseCode implements ErrorCode {

    FAILURE("10001", "failure"),
    PARAM_WRONG("10002", "param is wrong"),
    UNAUTHORIZED("10003", "unauthorized"),
    UNKNOWN_ERROR("20001", "unknown error occurred"),
    SERVER_NOT_AVAILABLE("20002", "the server is not available"),
    ;

    private final String code;
    private final String msg;
}
