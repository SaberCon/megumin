package cn.sabercon.common.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static cn.sabercon.common.util.TimeUtils.*;

/**
 * @author SaberCon
 * @since 1.0.0
 */
@Configuration
public class JsonConfig {

    @Bean
    @Primary
    public ObjectMapper objectMapper() {
        var mapper = new ObjectMapper();
        // 配置时间模块
        var module = new JavaTimeModule().addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DATE_TIME_FORMATTER))
                .addSerializer(LocalDate.class, new LocalDateSerializer(DATE_FORMATTER))
                .addSerializer(LocalTime.class, new LocalTimeSerializer(TIME_FORMATTER))
                .addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DATE_TIME_FORMATTER))
                .addDeserializer(LocalDate.class, new LocalDateDeserializer(DATE_FORMATTER))
                .addDeserializer(LocalTime.class, new LocalTimeDeserializer(TIME_FORMATTER));
        mapper.registerModule(module);
        // 保持 spring mvc 默认配置
        mapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, false);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        // 自定义配置
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        // 设置 mapper 到工具类
        Json.setMapper(mapper);
        return mapper;
    }
}
