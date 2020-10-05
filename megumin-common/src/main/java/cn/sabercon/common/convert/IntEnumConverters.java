package cn.sabercon.common.convert;

import cn.sabercon.common.enums.IntEnum;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.stereotype.Component;

/**
 * {@link IntEnum} 相关的转换器
 *
 * @author SaberCon
 * @since 1.0.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IntEnumConverters {

    @Component
    public static class IntEnumToIntConverter implements Converter<IntEnum, Integer> {
        @Override
        public Integer convert(IntEnum source) {
            return source.val();
        }
    }

    @Component
    public static class IntToIntEnumConverterFactory implements ConverterFactory<Integer, IntEnum> {
        @Override
        public <T extends IntEnum> Converter<Integer, T> getConverter(Class<T> targetType) {
            return source -> IntEnum.convert(targetType, source);
        }
    }

    @Component
    public static class IntEnumToStrConverter implements Converter<IntEnum, String> {
        @Override
        public String convert(IntEnum source) {
            return String.valueOf(source.val());
        }
    }

    @Component
    public static class StrToIntEnumConverterFactory implements ConverterFactory<String, IntEnum> {
        @Override
        public <T extends IntEnum> Converter<String, T> getConverter(Class<T> targetType) {
            return source -> IntEnum.convert(targetType, source);
        }
    }
}
