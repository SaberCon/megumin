package cn.sabercon.main.config.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author SaberCon
 * @since 1.0.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "aliyun")
public class AliyunProperties {

    private String accessKeyId;

    private String accessKeySecret;

    private SmsProperties sms;

    private OssProperties oss;

    @Data
    public static class SmsProperties {

        private String signName;

        private String templateCode;
    }

    @Data
    public static class OssProperties {

        private String endpoint;

        private String bucket;

        private String roleArn;

        private String accessDomain;
    }
}
