package cn.sabercon.main;

import cn.hutool.crypto.SecureUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author SaberCon
 * @since 1.0.0
 */
@SpringBootTest
class CommonTest {

    @Test
    void test() {
        System.out.println(SecureUtil.md5((String) null));
    }
}
