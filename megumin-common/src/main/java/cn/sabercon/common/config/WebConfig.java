package cn.sabercon.common.config;

import cn.sabercon.common.convert.IntEnumConverterFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * web 配置
 *
 * @author SaberCon
 * @since 1.0.0
 */
@Configuration
@ConditionalOnWebApplication
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverterFactory(new IntEnumConverterFactory());
    }
}
