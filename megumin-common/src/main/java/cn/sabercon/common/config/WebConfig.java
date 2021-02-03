package cn.sabercon.common.config;

import cn.sabercon.common.enums.IntEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;
import java.util.stream.Collectors;

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

    private final ConverterFactory<String, Enum> strToEnumConverterFactory;
    private final ConverterFactory<String, IntEnum> strToIntEnumConverterFactory;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverterFactory(strToEnumConverterFactory);
        registry.addConverterFactory(strToIntEnumConverterFactory);
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 避免 string 先被 StringHttpMessageConverter 处理导致无法返回 json 包装类, 将他们移到最后
        var stringConverters = converters.stream().filter(c -> c instanceof StringHttpMessageConverter).collect(Collectors.toList());
        converters.removeIf(c -> c instanceof StringHttpMessageConverter);
        converters.addAll(stringConverters);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}
