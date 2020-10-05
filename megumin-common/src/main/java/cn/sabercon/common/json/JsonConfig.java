package cn.sabercon.common.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
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

import static cn.sabercon.common.time.TimeUtils.*;

/**
 * 自定义全局的 json 操作
 *
 * @author SaberCon
 * @since 1.0.0
 */
@Configuration
public class JsonConfig {

    @Bean
    @Primary
    public ObjectMapper objectMapper() {
        var mapper = new ObjectMapper();
        applySpringMvcDefaultSetting(mapper);
        applyCustomSetting(mapper);

        // 设置 mapper 到工具类
        Json.setMapper(mapper);
        return mapper;
    }

    private void applySpringMvcDefaultSetting(ObjectMapper mapper) {
        mapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, false);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    private void applyCustomSetting(ObjectMapper mapper) {
        mapper.registerModule(timeModule());
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    private SimpleModule timeModule() {
        return new JavaTimeModule().addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(SECOND_FORMATTER))
                .addSerializer(LocalDate.class, new LocalDateSerializer(DATE_FORMATTER))
                .addSerializer(LocalTime.class, new LocalTimeSerializer(TIME_FORMATTER))
                .addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(SECOND_FORMATTER))
                .addDeserializer(LocalDate.class, new LocalDateDeserializer(DATE_FORMATTER))
                .addDeserializer(LocalTime.class, new LocalTimeDeserializer(TIME_FORMATTER));
    }
}
