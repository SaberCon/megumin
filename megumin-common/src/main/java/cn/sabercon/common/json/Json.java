package cn.sabercon.common.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
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

    public static String write(Object value) {
        try {
            return mapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            log.error("json 序列化失败, 原值: {}", value);
            return null;
        }
    }

    public static <T> T read(String content, TypeReference<T> valueTypeRef) {
        try {
            return mapper.readValue(content, valueTypeRef);
        } catch (JsonProcessingException e) {
            log.error("json 反序列化失败, 原值: {}", content);
            return null;
        }
    }

    public static <T> T read(String content, Class<T> valueType) {
        try {
            return mapper.readValue(content, valueType);
        } catch (JsonProcessingException e) {
            log.error("json 反序列化失败, 原值: {}", content);
            return null;
        }
    }

    public static JsonNode read(String content) {
        try {
            return mapper.readTree(content);
        } catch (JsonProcessingException e) {
            log.error("json 反序列化失败, 原值: {}", content);
            return null;
        }
    }

    public static <T> List<T> readList(String content, Class<T> valueType) {
        try {
            var collectionType = mapper.getTypeFactory().constructCollectionType(List.class, valueType);
            return mapper.readValue(content, collectionType);
        } catch (JsonProcessingException e) {
            log.error("json 反序列化失败, 原值: {}", content);
            return Collections.emptyList();
        }
    }

    public static <T> T convert(Object fromValue, Class<T> toValueType) {
        return mapper.convertValue(fromValue, toValueType);
    }
}
