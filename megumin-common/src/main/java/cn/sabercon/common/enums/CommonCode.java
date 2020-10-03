package cn.sabercon.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

/**
 * 常用的基础类状态码
 *
 * @author SaberCon
 * @since 1.0.0
 */
@Getter
@AllArgsConstructor
@Accessors(fluent = true)
public enum CommonCode implements ResultCode {

    SUCCESS("A0001", "success"),
    FAILURE("B0001", "failure"),
    PARAM_WRONG("B0002", "param is wrong"),
    ASSERTION_FAILURE("B0003", "the assertion failed"),
    SERVER_NOT_AVAILABLE("C0001", "the server is not available"),
    REMOTE_SERVICE_NOT_AVAILABLE("D0001", "the remote server is not available"),
    ;

    private final String code;
    private final String msg;
}
