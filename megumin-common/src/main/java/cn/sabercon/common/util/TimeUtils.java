package cn.sabercon.common.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * @author SaberCon
 * @since 1.0.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TimeUtils {

    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);
    private static final String TIME_FORMAT = "HH:mm:ss";
    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern(TIME_FORMAT);

    public static String format(LocalDateTime dateTime) {
        return DATE_TIME_FORMATTER.format(dateTime);
    }

    public static String format(LocalDate date) {
        return DATE_FORMATTER.format(date);
    }

    public static String format(LocalTime time) {
        return TIME_FORMATTER.format(time);
    }

    public static LocalDateTime parseDateTime(CharSequence text) {
        return DATE_TIME_FORMATTER.parse(text, LocalDateTime::from);
    }

    public static LocalDate parseDate(CharSequence text) {
        return DATE_FORMATTER.parse(text, LocalDate::from);
    }

    public static LocalTime parseTime(CharSequence text) {
        return TIME_FORMATTER.parse(text, LocalTime::from);
    }
}
