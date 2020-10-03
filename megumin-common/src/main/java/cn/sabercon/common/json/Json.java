package cn.sabercon.common.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * json 处理的工具类
 *
 * @author SaberCon
 * @since 1.0.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Json {

    private static ObjectMapper mapper;

    /**
     * 设置 json 处理工具, 需要在容器启动期间设置好
     */
    static void setMapper(ObjectMapper mapper) {
        Json.mapper = mapper;
    }
}
