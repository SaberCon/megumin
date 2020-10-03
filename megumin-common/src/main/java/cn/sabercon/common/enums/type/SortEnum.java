package cn.sabercon.common.enums.type;

import cn.sabercon.common.enums.IntEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

/**
 * 排序类型
 *
 * @author SaberCon
 * @since 1.0.0
 */
@Getter
@AllArgsConstructor
@Accessors(fluent = true)
public enum SortEnum implements IntEnum {

    ASC(1, "升序"),
    DESC(2, "降序"),
    ;

    private final int val;
    private final String desc;
}
