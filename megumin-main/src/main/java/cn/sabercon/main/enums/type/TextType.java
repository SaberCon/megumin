package cn.sabercon.main.enums.type;

import cn.sabercon.common.enums.IntEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

/**
 * 文本类型
 *
 * @author SaberCon
 * @since 1.0.0
 */
@Getter
@AllArgsConstructor
@Accessors(fluent = true)
public enum TextType implements IntEnum {

    TEXT(1, "简单文本"),
    HTML(2, "HTML 文本"),
    MARKDOWN(3, "MARKDOWN 文本"),
    ;

    private final int val;
    private final String desc;
}
