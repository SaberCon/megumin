package cn.sabercon.common.convert;

import cn.sabercon.common.util.TimeUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * 时间转换器
 *
 * @author SaberCon
 * @since 1.0.0
 */
@Component
public class LocalDateConverter implements Converter<String, LocalDate> {

    @Override
    public LocalDate convert(String source) {
        return TimeUtils.parseDate(source);
    }
}
