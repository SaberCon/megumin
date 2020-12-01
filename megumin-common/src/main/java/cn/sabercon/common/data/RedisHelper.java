package cn.sabercon.common.data;

import cn.hutool.core.util.ClassUtil;
import cn.sabercon.common.json.Json;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.concurrent.TimeUnit;

/**
 * redis 操作协助类, 主要用于字符串类型操作
 *
 * @author SaberCon
 * @since 1.0.0
 */
@Component
@RequiredArgsConstructor
public class RedisHelper {

    private final StringRedisTemplate template;

    public StringRedisTemplate tpl() {
        return template;
    }

    /**
     * @return 直接返回基本类型, 所以不能用于事务场景
     */
    public boolean exists(String key) {
        return template.hasKey(key);
    }

    /**
     * @return 直接返回基本类型, 所以不能用于事务场景
     */
    public boolean expire(String key, long timeout, TimeUnit unit) {
        return template.expire(key, timeout, unit);
    }

    /**
     * @return 直接返回基本类型, 所以不能用于事务场景
     */
    public boolean delete(String key) {
        return template.delete(key);
    }

    public String get(String key) {
        return template.boundValueOps(key).get();
    }

    public Boolean getAsBool(String key) {
        var value = get(key);
        return ObjectUtils.isEmpty(value) ? null : Boolean.parseBoolean(value);
    }

    public Integer getAsInt(String key) {
        var value = get(key);
        return ObjectUtils.isEmpty(value) ? null : Integer.parseInt(value);
    }

    public Long getAsLong(String key) {
        var value = get(key);
        return ObjectUtils.isEmpty(value) ? null : Long.parseLong(value);
    }

    /**
     * 获取键对应的 json 字符串并解析为目标类
     */
    public <T> T get(String key, Class<T> beanType) {
        var value = get(key);
        return ObjectUtils.isEmpty(value) ? null : Json.read(value, beanType);
    }

    /**
     * @param value 除了基本类型, 其包装类和字符串类型, 其他类型都将采用 json 字符串保存
     */
    public void set(String key, Object value, long timeout, TimeUnit unit) {
        String valueStr;
        if (value instanceof String || ClassUtil.isBasicType(value.getClass())) {
            valueStr = value.toString();
        } else {
            valueStr = Json.write(value);
        }
        template.boundValueOps(key).set(valueStr, timeout, unit);
    }

    /**
     * 默认过期时间 1 天
     */
    public void set(String key, Object value) {
        set(key, value, 1, TimeUnit.DAYS);
    }
}
