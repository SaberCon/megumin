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

    /**
     * 多语言资源键值
     */
    private final String key;

    /**
     * 参数数组, 用于格式化信息
     */
    private final Object[] args;

    public ServiceException(String msg, String code, String key, Object... args) {
        super(msg);
        this.code = code;
        this.key = key;
        this.args = args;
    }
}
