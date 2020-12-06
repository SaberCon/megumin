package cn.sabercon.common.util;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.util.ReflectUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * bean 相关操作工具类
 *
 * @author SaberCon
 * @since 1.0.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PojoUtils {

    public static <T> T convert(Object source, Class<T> tClass) {
        T target = ReflectUtil.newInstance(tClass);
        copy(source, target);
        return target;
    }

    public static void copy(Object source, Object target) {
        BeanUtil.copyProperties(source, target, CopyOptions.create().ignoreError());
    }

    public static void copyIgnoreNull(Object source, Object target) {
        BeanUtil.copyProperties(source, target, CopyOptions.create().ignoreError().ignoreNullValue());
    }
}
