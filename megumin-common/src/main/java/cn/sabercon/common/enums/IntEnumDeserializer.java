package cn.sabercon.common.enums;

import cn.hutool.core.util.NumberUtil;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

/**
 * {@link IntEnum} 的反序列化器
 *
 * @author SaberCon
 * @since 1.0.0
 */
@Slf4j
public class IntEnumDeserializer extends JsonDeserializer<IntEnum> {

    @Override
    @SneakyThrows
    public IntEnum deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        if (Objects.isNull(p.getText()) || !NumberUtil.isInteger(p.getText())) {
            // 非整型不解析
            return null;
        }
        var targetClass = Objects.requireNonNull(BeanUtils.getPropertyDescriptor(
                p.getParsingContext().getCurrentValue().getClass(),
                p.getParsingContext().getCurrentName())).getPropertyType();
        if (!Enum.class.isAssignableFrom(targetClass) || !IntEnum.class.isAssignableFrom(targetClass)) {
            return null;
        }
        int value = p.getIntValue();
        return Arrays.stream(targetClass.getEnumConstants()).map(IntEnum.class::cast)
                .filter(e -> e.val() == value).findFirst().orElse(null);
    }
}
