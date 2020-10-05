package cn.sabercon.common.config;

import cn.sabercon.common.enums.IntEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author SaberCon
 * @since 1.0.0
 */
@Configuration
@RequiredArgsConstructor
public class MongoConfig {

    private final ConverterFactory<Integer, IntEnum> intToIntEnumConverterFactory;
    private final Converter<IntEnum, Integer> intEnumToIntConverter;
    private final Converter<String, LocalDateTime> strToLocalDateTimeConverter;
    private final Converter<LocalDateTime, String> localDateTimeToStrConverter;
    private final Converter<String, LocalDate> strToLocalDateConverter;
    private final Converter<LocalDate, String> localDateToStrConverter;
    private final Converter<String, LocalTime> strToLocalTimeConverter;
    private final Converter<LocalTime, String> localTimeToStrConverter;

    /**
     * @return 配置 mongo 转换器
     */
    @Bean
    public MongoCustomConversions customConversions() {
        return MongoCustomConversions.create(config -> config
                .registerConverterFactory(intToIntEnumConverterFactory)
                .registerConverter(intEnumToIntConverter)
                .registerConverter(strToLocalDateTimeConverter)
                .registerConverter(localDateTimeToStrConverter)
                .registerConverter(strToLocalDateConverter)
                .registerConverter(localDateToStrConverter)
                .registerConverter(strToLocalTimeConverter)
                .registerConverter(localTimeToStrConverter));
    }
}
