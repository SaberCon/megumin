package cn.sabercon.user;

import cn.hutool.core.date.DateUtil;
import cn.sabercon.common.enums.type.SortEnum;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

/**
 * @author SaberCon
 * @since 1.0.0
 */
@SpringBootTest
class CommonTest {

    @Test
    void test() {
        System.out.println("hello");
        System.out.println(System.getProperty("java.class.path"));
        System.out.println(DateUtil.format(new Date(), "yyyy-MM-dd"));
        System.out.println(SortEnum.ASC);
    }
}
