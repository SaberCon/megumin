package cn.sabercon.common.time;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * 集合 java8 时间相关的转换器
 *
 * @author SaberCon
 * @since 1.0.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TimeConverters {

    @Component
    public static class LocalDateTimeToStrConverter implements Converter<LocalDateTime, String> {
        @Override
        public String convert(LocalDateTime source) {
            return TimeUtils.format(source);
        }
    }

    @Component
    public static class StrToLocalDateTimeConverter implements Converter<String, LocalDateTime> {
        @Override
        public LocalDateTime convert(String source) {
            return TimeUtils.parseDateTime(source);
        }
    }

    @Component
    public static class LocalDateToStrConverter implements Converter<LocalDate, String> {
        @Override
        public String convert(LocalDate source) {
            return TimeUtils.format(source);
        }
    }

    @Component
    public static class StrToLocalDateConverter implements Converter<String, LocalDate> {
        @Override
        public LocalDate convert(String source) {
            return TimeUtils.parseDate(source);
        }
    }

    @Component
    public static class LocalTimeToStrConverter implements Converter<LocalTime, String> {

        @Override
        public String convert(LocalTime source) {
            return TimeUtils.format(source);
        }
    }

    @Component
    public static class StrToLocalTimeConverter implements Converter<String, LocalTime> {

        @Override
        public LocalTime convert(String source) {
            return TimeUtils.parseTime(source);
        }
    }
}
