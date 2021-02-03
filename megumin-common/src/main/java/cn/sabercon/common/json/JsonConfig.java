package cn.sabercon.common.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

import static cn.sabercon.common.util.TimeUtils.*;

/**
 * 自定义全局的 json 操作
 *
 * @author SaberCon
 * @since 1.0.0
 */
@Configuration
public class JsonConfig {

    /**
     * 自定义枚举类序列化方法, 采用小写形式
     */
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer enumCustomizer() {
        return jacksonObjectMapperBuilder -> jacksonObjectMapperBuilder.serializerByType(Enum.class, new JsonSerializer<Enum<?>>() {
            @Override
            public void serialize(Enum value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
                gen.writeString(value.name().toLowerCase());
            }
        }).deserializerByType(Enum.class, new JsonDeserializer<Enum<?>>() {
            @Override
            public Enum<?> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
                if (Objects.isNull(p.getText())) {
                    return null;
                }
                var targetClass = Objects.requireNonNull(BeanUtils.getPropertyDescriptor(
                        p.getParsingContext().getCurrentValue().getClass(),
                        p.getParsingContext().getCurrentName())).getPropertyType();
                return Enum.valueOf((Class<Enum>) targetClass, p.getText().toUpperCase());
            }
        });
    }

    @Bean
    @Primary
    public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder) {
        return builder.modulesToInstall(timeModule()).serializationInclusion(JsonInclude.Include.NON_NULL).build();
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
