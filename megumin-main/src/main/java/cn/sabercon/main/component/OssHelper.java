package cn.sabercon.main.component;

import cn.sabercon.main.config.property.AliyunProperties;
import cn.sabercon.main.domain.model.OssData;
import cn.sabercon.main.enums.type.FileType;
import com.aliyun.oss.OSS;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PolicyConditions;
import com.google.common.base.Joiner;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 短信相关操作类
 *
 * @author SaberCon
 * @since 1.0.0
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class OssHelper {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    private final OSS oss;
    private final AliyunProperties properties;

    /**
     * 上传文件
     */
    @SneakyThrows
    public String upload(MultipartFile file, FileType type, String filename) {
        // 拼接文件全路径名
        var fullName = Joiner.on("/").join(type.dir, FORMATTER.format(LocalDate.now()), filename);

        var metadata = new ObjectMetadata();
        metadata.setContentType(file.getContentType());
        metadata.setContentDisposition(type.contentDisposition);
        oss.putObject(properties.getOss().getBucket(), fullName, file.getInputStream(), metadata);
        return Joiner.on("/").join(properties.getOss().getAccessDomain(), fullName);
    }

    /**
     * @return 临时凭证
     */
    @SneakyThrows
    public OssData getOssData() {
        var expiration = new Date(System.currentTimeMillis() + 30 * 1000);
        var policyConditions = new PolicyConditions();
        // PostObject 请求最大可支持的文件大小为 5GB, 即 CONTENT_LENGTH_RANGE 为5*1024*1024*1024
        policyConditions.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 512L * 1024 * 1024);
        var postPolicy = oss.generatePostPolicy(expiration, policyConditions);
        String encodedPolicy = BinaryUtil.toBase64String(postPolicy.getBytes(StandardCharsets.UTF_8));
        String postSignature = oss.calculatePostSignature(postPolicy);
        return OssData.builder().accessId(properties.getAccessKeyId())
                .policy(encodedPolicy)
                .signature(postSignature)
                .host(properties.getOss().getAccessDomain())
                .expire(expiration.getTime()).build();
    }
}
