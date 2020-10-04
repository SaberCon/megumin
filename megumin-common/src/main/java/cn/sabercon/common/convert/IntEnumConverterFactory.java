package cn.sabercon.common.convert;

import cn.sabercon.common.enums.IntEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

/**
 * {@link IntEnumConverter} 的构造工厂
 *
 * @author SaberCon
 * @since 1.0.0
 */
public class IntEnumConverterFactory implements ConverterFactory<String, IntEnum> {

    @Override
    public <T extends IntEnum> Converter<String, T> getConverter(Class<T> targetType) {
        return new IntEnumConverter<>(targetType);
    }
}
