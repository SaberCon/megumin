package cn.sabercon.common.enums.type;

import cn.sabercon.common.enums.IntEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.springframework.data.domain.Sort;

/**
 * 排序类型
 *
 * @author SaberCon
 * @since 1.0.0
 */
@Getter
@AllArgsConstructor
@Accessors(fluent = true)
public enum SortType implements IntEnum {

    ASC(1, "升序", Sort.Direction.ASC),
    DESC(2, "降序", Sort.Direction.DESC),
    ;

    private final int val;
    private final String desc;
    private final Sort.Direction direction;
}
