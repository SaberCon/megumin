package cn.sabercon.common;

import cn.sabercon.common.enums.IntEnum;
import cn.sabercon.common.enums.type.SortEnum;
import org.junit.jupiter.api.Test;

/**
 * @author SaberCon
 * @since 1.0.0
 */
class SimpleTest {

    @Test
    void test() {
        var anEnum = IntEnum.convert(SortEnum.class, 2);
        System.out.println(anEnum);
    }
}
