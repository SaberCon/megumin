package cn.sabercon.common.domian;

import cn.sabercon.common.enums.CommonCode;
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

    private static final Result<?> DEFAULT_SUCCESS = new Result<>(CommonCode.SUCCESS.code(), null, null);
    /**
     * 状态码
     */
    String code;
    /**
     * 状态信息
     */
    String msg;
    /**
     * 返回数据
     */
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
        return new Result<>(CommonCode.SUCCESS.code(), null, data);
    }

    public static <T> Result<T> fail(String code, String msg) {
        return new Result<>(code, msg, null);
    }
}
