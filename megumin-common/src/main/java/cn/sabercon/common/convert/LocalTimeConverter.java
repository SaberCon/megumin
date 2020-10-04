package cn.sabercon.common.convert;

import cn.sabercon.common.util.TimeUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

/**
 * 时间转换器
 *
 * @author SaberCon
 * @since 1.0.0
 */
@Component
public class LocalTimeConverter implements Converter<String, LocalTime> {

    @Override
    public LocalTime convert(String source) {
        return TimeUtils.parseTime(source);
    }
}
