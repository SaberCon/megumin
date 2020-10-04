package cn.sabercon.common.convert;

import cn.sabercon.common.enums.IntEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;

/**
 * 实现用整型代表 {@link IntEnum} 枚举值的转换器， 用于 controller 方法参数和数据绑定
 *
 * @author SaberCon
 * @since 1.0.0
 */
@RequiredArgsConstructor
public class IntEnumConverter<T extends IntEnum> implements Converter<String, T> {

    /**
     * 要转换成的目标枚举类
     */
    private final Class<T> targetType;

    @Override
    public T convert(String source) {
        try {
            return IntEnum.convert(targetType, Integer.parseInt(source));
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
