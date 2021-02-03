package cn.sabercon.common.convert;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.stereotype.Component;

/**
 * 使用小写转化枚举类的转换器
 *
 * @author SaberCon
 * @since 1.0.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EnumConverters {

    @Component
    public static class EnumToStrConverter implements Converter<Enum, String> {
        @Override
        public String convert(Enum source) {
            return source.name().toLowerCase();
        }
    }

    @Component
    public static class StrToEnumConverterFactory implements ConverterFactory<String, Enum> {
        @Override
        public <T extends Enum> Converter<String, T> getConverter(Class<T> targetType) {
            return source -> (T) Enum.valueOf((Class<Enum>) targetType, source.toUpperCase());
        }
    }
}
