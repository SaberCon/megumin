package cn.sabercon.common.exception;

import lombok.Getter;

/**
 * 通用的业务异常
 *
 * @author SaberCon
 * @since 1.0.0
 */
@Getter
public class ServiceException extends RuntimeException {

    /**
     * 错误码
     */
    private final String code;

    public ServiceException(String msg, String code) {
        super(msg);
        this.code = code;
    }
}
