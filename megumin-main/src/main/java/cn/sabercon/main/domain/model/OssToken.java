package cn.sabercon.main.domain.model;

import lombok.Builder;
import lombok.Data;

/**
 * oss 前端上传需要的临时参数
 *
 * @author SaberCon
 * @since 1.0.0
 */
@Data
@Builder
public class OssToken {

    private String accessKeyId;

    private String accessKeySecret;

    private String securityToken;

    private String expiration;

    private String endpoint;

    private String bucket;

    private String accessDomain;

    private String dir;
}
