package cn.sabercon.common.json;

import cn.hutool.core.util.NumberUtil;
import cn.sabercon.common.enums.IntEnum;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
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
        var value = p.getText();
        if (Objects.isNull(value) || !NumberUtil.isInteger(value)) {
            return null;
        }
        var context = p.getParsingContext();
        var enumClass = (Class<IntEnum>) context.getCurrentValue().getClass()
                .getDeclaredField(context.getCurrentName()).getType();
        return IntEnum.convert(enumClass, Integer.parseInt(value));
    }
}
