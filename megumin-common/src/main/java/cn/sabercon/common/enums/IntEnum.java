package cn.sabercon.common.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Arrays;
import java.util.Objects;

/**
 * 用整型表示数值的枚举类的通用接口
 *
 * @author SaberCon
 * @since 1.0.0
 */
@JsonDeserialize(using = IntEnumDeserializer.class)
public interface IntEnum {

    /**
     * @return 代表枚举项的整型值
     */
    @JsonValue
    int val();

    /**
     * @return 枚举项的描述信息
     */
    default String desc() {
        return null;
    }

    /**
     * @return 将给定值转换为对应枚举的枚举项, 转换失败时返回 null
     */
    static <T extends IntEnum> T convert(Class<T> enumClass, Integer val) {
        if (Objects.isNull(val)) {
            return null;
        }
        return Arrays.stream(enumClass.getEnumConstants()).filter(e -> e.val() == val).findFirst().orElse(null);
    }

    /**
     * @return 将给定值转换为对应枚举的枚举项, 转换失败时返回 null
     */
    static <T extends IntEnum> T convert(Class<T> enumClass, String val) {
        if (Objects.isNull(val)) {
            return null;
        }
        try {
            return convert(enumClass, Integer.parseInt(val));
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
