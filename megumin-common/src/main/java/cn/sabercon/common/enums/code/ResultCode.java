package cn.sabercon.common.enums.code;

import cn.sabercon.common.exception.ServiceException;

/**
 * 结果状态码枚举类实现接口
 *
 * @author SaberCon
 * @since 1.0.0
 */
public interface ResultCode {

    /**
     * @return 状态码
     */
    String code();

    /**
     * @return 状态信息
     */
    default String msg() {
        return null;
    }

    /**
     * @return 多语言资源键值
     */
    default String key() {
        return null;
    }

    default ServiceException exceptionWithMsg(String msg, Object... args) {
        return new ServiceException(msg, code(), key(), args);
    }

    default ServiceException exception(Object... args) {
        return exceptionWithMsg(msg(), args);
    }
}
