package cn.sabercon.main;

import cn.sabercon.common.data.RedisHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author SaberCon
 * @since 1.0.0
 */
@EnableScheduling
@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class);
    }

    @Autowired
    RedisHelper redisHelper;

    /**
     * todo redis 现在有问题, 需要不间断使用不然会断开连接, 待处理
     */
    @Scheduled(fixedRate = 10 * 1000)
    public void keepRedisConnection() {
        System.out.println("redis 保持连接中...");
        redisHelper.set("keep-alive", 0);
    }
}
