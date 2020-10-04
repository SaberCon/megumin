package cn.sabercon.common.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * 时间工具类
 *
 * @author SaberCon
 * @since 1.0.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TimeUtils {

    private static final String SECOND_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final DateTimeFormatter SECOND_FORMATTER = DateTimeFormatter.ofPattern(SECOND_FORMAT);
    private static final String MINUTE_FORMAT = "yyyy-MM-dd HH:mm";
    public static final DateTimeFormatter MINUTE_FORMATTER = DateTimeFormatter.ofPattern(MINUTE_FORMAT);
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);
    private static final String TIME_FORMAT = "HH:mm:ss";
    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern(TIME_FORMAT);

    public static String format(LocalDateTime dateTime) {
        return SECOND_FORMATTER.format(dateTime);
    }

    public static String format(LocalDate date) {
        return DATE_FORMATTER.format(date);
    }

    public static String format(LocalTime time) {
        return TIME_FORMATTER.format(time);
    }

    public static LocalDateTime parseDateTime(CharSequence text) {
        // 解析分钟或秒格式
        if (SECOND_FORMAT.length() == text.length()) {
            return SECOND_FORMATTER.parse(text, LocalDateTime::from);
        } else if (MINUTE_FORMAT.length() == text.length()) {
            return MINUTE_FORMATTER.parse(text, LocalDateTime::from);
        }
        // 当成日期解析
        return parseDate(text).atStartOfDay();
    }

    public static LocalDate parseDate(CharSequence text) {
        return DATE_FORMATTER.parse(text, LocalDate::from);
    }

    public static LocalTime parseTime(CharSequence text) {
        return TIME_FORMATTER.parse(text, LocalTime::from);
    }
}
