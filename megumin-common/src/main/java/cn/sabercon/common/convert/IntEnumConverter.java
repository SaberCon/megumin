package cn.sabercon.common.convert;

import cn.sabercon.common.enums.IntEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;

/**
 * @author SaberCon
 * @since 1.0.0
 */
@RequiredArgsConstructor
public class IntEnumConverter<T extends IntEnum> implements Converter<String, T> {

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
