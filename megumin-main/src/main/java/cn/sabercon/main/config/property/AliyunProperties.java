package cn.sabercon.main.config.property;

import lombok.Data;
import org.hibernate.validator.constraints.URL;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * 阿里云配置参数类
 *
 * @author SaberCon
 * @since 1.0.0
 */
@Data
@Validated
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

        @URL
        private String accessDomain;
    }
}
