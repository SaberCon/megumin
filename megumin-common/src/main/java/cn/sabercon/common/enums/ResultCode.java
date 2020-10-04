package cn.sabercon.common.enums;

import cn.sabercon.common.exception.ServiceException;

import java.util.Optional;
import java.util.function.Supplier;

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

    /**
     * 抛出以当前状态码为错误码的异常
     */
    default void throwExceptionWithMsg(String msg, Object... args) {
        throw new ServiceException(msg, code(), key(), args);
    }

    default void throwException(Object... args) {
        throwExceptionWithMsg(msg(), args);
    }

    /**
     * 主要用于简化 {@link Optional#orElseThrow(Supplier)} 的代码
     */
    default Supplier<ServiceException> exceptionSupplierWithMsg(String msg, Object... args) {
        return () -> new ServiceException(msg, code(), key(), args);
    }

    default Supplier<ServiceException> exceptionSupplier(Object... args) {
        return exceptionSupplierWithMsg(msg(), args);
    }
}
