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
public class OssData {

    private String accessId;

    private String policy;

    private String signature;

    private String host;

    private Long expire;
}
