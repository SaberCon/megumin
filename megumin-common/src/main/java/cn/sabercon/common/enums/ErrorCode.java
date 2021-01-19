package cn.sabercon.common.enums;

import cn.sabercon.common.exception.ServiceException;

/**
 * 错误码枚举类实现接口
 *
 * @author SaberCon
 * @since 1.0.0
 */
public interface ErrorCode {

    String code();

    default String msg() {
        return null;
    }

    default ServiceException exception(String msg) {
        return new ServiceException(msg, code());
    }

    default ServiceException exception() {
        return exception(msg());
    }
}
