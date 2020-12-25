package cn.sabercon.common.domian;

import lombok.Value;

import java.util.Objects;

/**
 * 通用的返回对象
 *
 * @author SaberCon
 * @since 1.0.0
 */
@Value
public class Result<T> {

    private static final Result<?> DEFAULT_SUCCESS = new Result<>(true, null, null, null, null);

    boolean success;

    String errorCode;

    String errorMessage;

    String debugMessage;

    T data;

    /**
     * @return 不可变的无数据成功对象
     */
    @SuppressWarnings("unchecked")
    public static <T> Result<T> ok() {
        return (Result<T>) DEFAULT_SUCCESS;
    }

    public static <T> Result<T> ok(T data) {
        if (Objects.isNull(data)) {
            return ok();
        }
        return new Result<>(true, null, null, null, data);
    }

    public static <T> Result<T> fail(String code, String msg, String debugMsg) {
        return new Result<>(false, code, msg, debugMsg, null);
    }

    public static <T> Result<T> fail(String code, String msg) {
        return fail(code, msg, null);
    }
}
