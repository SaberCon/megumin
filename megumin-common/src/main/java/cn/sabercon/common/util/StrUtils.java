package cn.sabercon.common.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 字符串相关操作工具类
 *
 * @author SaberCon
 * @since 1.0.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StrUtils {

    /**
     * @return 用 {@code :} 拼接好的 redis 键名
     */
    public static String buildRedisKey(Object... parts) {
        return Arrays.stream(parts).map(String::valueOf).collect(Collectors.joining(":"));
    }
}
