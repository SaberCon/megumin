package cn.sabercon.main;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author SaberCon
 * @since 1.0.0
 */
@SpringBootTest
class CommonTest {

    @Autowired
    StringRedisTemplate redisTemplate;

    @Test
    void test() {
        redisTemplate.slaveOfNoOne();
        redisTemplate.execute((RedisCallback<Object>) c -> {
            c.setConfig("stop-writes-on-bgsave-error", "no");
            return null;
        });
        for (String key : redisTemplate.keys("*")) {
            System.out.println(key);
        }
    }
}
