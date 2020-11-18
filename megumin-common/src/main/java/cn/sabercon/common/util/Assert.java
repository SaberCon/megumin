package cn.sabercon.common.util;

import cn.sabercon.common.enums.CommonCode;
import cn.sabercon.common.enums.ResultCode;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Objects;

/**
 * 断言工具类
 *
 * @author SaberCon
 * @since 1.0.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Assert {

    public static void isTrue(boolean condition, ResultCode resultCode, String msg) {
        if (condition) {
            return;
        }
        throw resultCode.exceptionWithMsg(msg);
    }

    public static void isTrue(boolean condition, ResultCode resultCode) {
        isTrue(condition, resultCode, resultCode.msg());
    }

    public static void isTrue(boolean condition, String msg) {
        isTrue(condition, CommonCode.FAILURE, msg);
    }

    public static void isTrue(boolean condition) {
        isTrue(condition, CommonCode.ASSERTION_FAILURE);
    }

    public static void isFalse(boolean condition, ResultCode resultCode, String msg) {
        isTrue(!condition, resultCode, msg);
    }

    public static void isFalse(boolean condition, ResultCode resultCode) {
        isFalse(condition, resultCode, resultCode.msg());
    }

    public static void isFalse(boolean condition, String msg) {
        isFalse(condition, CommonCode.FAILURE, msg);
    }

    public static void isFalse(boolean condition) {
        isFalse(condition, CommonCode.ASSERTION_FAILURE);
    }

    public static void isNull(Object obj, ResultCode resultCode, String msg) {
        isTrue(Objects.isNull(obj), resultCode, msg);
    }

    public static void isNull(Object obj, ResultCode resultCode) {
        isNull(obj, resultCode, resultCode.msg());
    }

    public static void isNull(Object obj, String msg) {
        isNull(obj, CommonCode.FAILURE, msg);
    }

    public static void isNull(Object obj) {
        isNull(obj, CommonCode.ASSERTION_FAILURE);
    }

    @NotNull
    public static <T> T notNull(T obj, ResultCode resultCode, String msg) {
        isTrue(Objects.nonNull(obj), resultCode, msg);
        return obj;
    }

    @NotNull
    public static <T> T notNull(T obj, ResultCode resultCode) {
        return notNull(obj, resultCode, resultCode.msg());
    }

    @NotNull
    public static <T> T notNull(T obj, String msg) {
        return notNull(obj, CommonCode.FAILURE, msg);
    }

    @NotNull
    public static <T> T notNull(T obj) {
        return notNull(obj, CommonCode.ASSERTION_FAILURE);
    }

    public static void notEmpty(String str, ResultCode resultCode, String msg) {
        isTrue(Objects.nonNull(str) && !str.isEmpty(), resultCode, msg);
    }

    public static void notEmpty(String str, ResultCode resultCode) {
        notEmpty(str, resultCode, resultCode.msg());
    }

    public static void notEmpty(String str, String msg) {
        notEmpty(str, CommonCode.FAILURE, msg);
    }

    public static void notEmpty(String str) {
        notEmpty(str, CommonCode.ASSERTION_FAILURE);
    }

    public static void notEmpty(Collection<?> coll, ResultCode resultCode, String msg) {
        isTrue(Objects.nonNull(coll) && !coll.isEmpty(), resultCode, msg);
    }

    public static void notEmpty(Collection<?> coll, ResultCode resultCode) {
        notEmpty(coll, resultCode, resultCode.msg());
    }

    public static void notEmpty(Collection<?> coll, String msg) {
        notEmpty(coll, CommonCode.FAILURE, msg);
    }

    public static void notEmpty(Collection<?> coll) {
        notEmpty(coll, CommonCode.ASSERTION_FAILURE);
    }
}
