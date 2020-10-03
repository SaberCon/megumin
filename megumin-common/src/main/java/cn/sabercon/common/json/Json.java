package cn.sabercon.common.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * json 处理的工具类
 *
 * @author SaberCon
 * @since 1.0.0
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Json {

    private static ObjectMapper mapper;

    /**
     * 设置 json 处理工具, 需要在容器启动期间设置好
     */
    static void setMapper(ObjectMapper mapper) {
        Json.mapper = mapper;
    }

    public static ObjectMapper getMapper() {
        return mapper;
    }

    @SneakyThrows
    public static String write(Object value) {
        return mapper.writeValueAsString(value);

    }

    @SneakyThrows
    public static <T> T read(String content, TypeReference<T> valueTypeRef) {
        return mapper.readValue(content, valueTypeRef);
    }

    @SneakyThrows
    public static <T> T read(String content, Class<T> valueType) {
        return mapper.readValue(content, valueType);

    }

    @SneakyThrows
    public static JsonNode read(String content) {
        return mapper.readTree(content);
    }

    @SneakyThrows
    public static <T> List<T> readList(String content, Class<T> valueType) {
        var collectionType = mapper.getTypeFactory().constructCollectionType(List.class, valueType);
        return mapper.readValue(content, collectionType);
    }

    public static <T> T convert(Object fromValue, Class<T> toValueType) {
        return mapper.convertValue(fromValue, toValueType);
    }
}
