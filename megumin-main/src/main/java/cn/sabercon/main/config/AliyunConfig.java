package cn.sabercon.main.config;

import cn.sabercon.main.config.property.AliyunProperties;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * aliyun 相关配置
 *
 * @author SaberCon
 * @since 1.0.0
 */
@Configuration
@RequiredArgsConstructor
public class AliyunConfig {

    private final AliyunProperties properties;

    @Bean
    public OSS ossClient() {
        return new OSSClientBuilder().build(properties.getOss().getEndpoint(), properties.getAccessKeyId(), properties.getAccessKeySecret());
    }

    @Bean
    public IAcsClient acsClient() {
        return new DefaultAcsClient(DefaultProfile.getProfile("cn-shenzhen", properties.getAccessKeyId(), properties.getAccessKeySecret()));
    }
}
