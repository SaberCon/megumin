package cn.sabercon.common.util;

import cn.sabercon.common.enums.BaseCode;
import cn.sabercon.common.enums.ErrorCode;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.util.ObjectUtils;

import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * 断言工具类
 *
 * @author SaberCon
 * @since 1.0.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Checker {

    public static void isTrue(boolean condition, ErrorCode resultCode, String msg) {
        if (condition) {
            return;
        }
        throw resultCode.exception(msg);
    }

    public static void isTrue(boolean condition, ErrorCode resultCode) {
        isTrue(condition, resultCode, resultCode.msg());
    }

    public static void isTrue(boolean condition, String msg) {
        isTrue(condition, BaseCode.FAILURE, msg);
    }

    public static void isFalse(boolean condition, ErrorCode resultCode, String msg) {
        isTrue(!condition, resultCode, msg);
    }

    public static void isFalse(boolean condition, ErrorCode resultCode) {
        isFalse(condition, resultCode, resultCode.msg());
    }

    public static void isFalse(boolean condition, String msg) {
        isFalse(condition, BaseCode.FAILURE, msg);
    }

    public static void isNull(Object obj, ErrorCode resultCode, String msg) {
        isTrue(Objects.isNull(obj), resultCode, msg);
    }

    public static void isNull(Object obj, ErrorCode resultCode) {
        isNull(obj, resultCode, resultCode.msg());
    }

    public static void isNull(Object obj, String msg) {
        isNull(obj, BaseCode.FAILURE, msg);
    }

    @NotNull
    public static <T> T notNull(T obj, ErrorCode resultCode, String msg) {
        isTrue(Objects.nonNull(obj), resultCode, msg);
        return obj;
    }

    @NotNull
    public static <T> T notNull(T obj, ErrorCode resultCode) {
        return notNull(obj, resultCode, resultCode.msg());
    }

    @NotNull
    public static <T> T notNull(T obj, String msg) {
        return notNull(obj, BaseCode.FAILURE, msg);
    }

    public static void notEmpty(Object obj, ErrorCode resultCode, String msg) {
        isTrue(!ObjectUtils.isEmpty(obj), resultCode, msg);
    }

    public static void notEmpty(Object obj, ErrorCode resultCode) {
        notEmpty(obj, resultCode, resultCode.msg());
    }

    public static void notEmpty(Object obj, String msg) {
        notEmpty(obj, BaseCode.FAILURE, msg);
    }
}
