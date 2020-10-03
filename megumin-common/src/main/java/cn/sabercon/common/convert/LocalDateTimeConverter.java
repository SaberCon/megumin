package cn.sabercon.common.convert;

import cn.sabercon.common.util.TimeUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author SaberCon
 * @since 1.0.0
 */
@Component
public class LocalDateTimeConverter implements Converter<String, LocalDateTime> {

    @Override
    public LocalDateTime convert(String source) {
        return TimeUtils.parseDateTime(source);
    }
}
