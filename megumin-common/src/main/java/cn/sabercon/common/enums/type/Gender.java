package cn.sabercon.common.enums.type;

import cn.sabercon.common.enums.IntEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

/**
 * 性别
 *
 * @author SaberCon
 * @since 1.0.0
 */
@Getter
@AllArgsConstructor
@Accessors(fluent = true)
public enum Gender implements IntEnum {

    MALE(1, "男"),
    FEMALE(2, "女"),
    ;

    private final int val;
    private final String desc;
}
