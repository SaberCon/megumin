package cn.sabercon.common.config.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 线程池配置参数类
 *
 * @author SaberCon
 * @since 1.0.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "sabercon.thread-pool")
public class ThreadPoolProperties {

    private int corePoolSize = 10;

    private int maxPoolSize = 30;

    private int keepAliveSeconds = 60;

    private int queueCapacity = 50;
}
