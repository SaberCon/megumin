package cn.sabercon.common.json;

import cn.sabercon.common.util.Assert;
import cn.sabercon.common.util.ContextHolder;
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

    static {
        // 设置好 mapper 注入的回调
        ContextHolder.addCallBack(context -> mapper = context.getBean(ObjectMapper.class));
    }

    public static ObjectMapper getMapper() {
        return Assert.notNull(mapper, "the object mapper has not been set, please access this method after the context is ready");
    }

    /**
     * @return 对象序列化后的 json 字符串
     */
    @SneakyThrows
    public static String write(Object value) {
        return getMapper().writeValueAsString(value);

    }

    /**
     * @return json 解析后的对象
     */
    @SneakyThrows
    public static <T> T read(String content, TypeReference<T> valueTypeRef) {
        return getMapper().readValue(content, valueTypeRef);
    }

    /**
     * @return json 解析后的对象
     */
    @SneakyThrows
    public static <T> T read(String content, Class<T> valueType) {
        return getMapper().readValue(content, valueType);

    }

    /**
     * @return json 解析后的对象
     */
    @SneakyThrows
    public static JsonNode read(String content) {
        return getMapper().readTree(content);
    }

    /**
     * @return json 解析后的列表对象
     */
    @SneakyThrows
    public static <T> List<T> readList(String content, Class<T> valueType) {
        var collectionType = getMapper().getTypeFactory().constructCollectionType(List.class, valueType);
        return getMapper().readValue(content, collectionType);
    }

    /**
     * 通过 json 转化拷贝源对象的属性到一个新的对象
     *
     * @param fromValue   源对象
     * @param toValueType 目标对象类型
     */
    public static <T> T convert(Object fromValue, Class<T> toValueType) {
        return getMapper().convertValue(fromValue, toValueType);
    }
}
