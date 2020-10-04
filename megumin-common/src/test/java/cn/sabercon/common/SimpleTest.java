package cn.sabercon.common;

import cn.sabercon.common.util.TimeUtils;
import org.junit.jupiter.api.Test;

/**
 * @author SaberCon
 * @since 1.0.0
 */
class SimpleTest {

    @Test
    void test() throws Exception {
        System.out.println(TimeUtils.parseDateTime("2020-11-11"));
    }
}
