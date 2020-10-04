package cn.sabercon.common.config;

import cn.sabercon.common.enums.IntEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * web 配置
 *
 * @author SaberCon
 * @since 1.0.0
 */
@Configuration
@RequiredArgsConstructor
@ConditionalOnWebApplication
public class WebConfig implements WebMvcConfigurer {

    private final ConverterFactory<String, IntEnum> strToIntEnumConverterFactory;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverterFactory(strToIntEnumConverterFactory);
    }
}
