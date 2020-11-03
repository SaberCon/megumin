package cn.sabercon.main.manager;

import cn.sabercon.main.domain.model.OssToken;
import cn.sabercon.main.enums.type.FileType;
import com.aliyun.oss.OSS;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.auth.sts.AssumeRoleRequest;
import com.aliyuncs.http.MethodType;
import com.google.common.base.Joiner;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * 短信相关操作类
 *
 * @author SaberCon
 * @since 1.0.0
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class OssManager {

    private final OSS oss;

    private final IAcsClient acsClient;

    @Value("${aliyun.oss.endpoint}")
    private String endpoint;
    @Value("${aliyun.oss.bucket}")
    private String bucket;
    @Value("${aliyun.oss.role-arn}")
    private String roleArn;
    @Value("${aliyun.oss.access-domain}")
    private String accessDomain;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd");

    /**
     * 上传文件
     */
    @SneakyThrows
    public String upload(MultipartFile file, FileType type, String filename) {
        // 拼接文件全路径名
        var fullName = Joiner.on("/").join(type.dir(), FORMATTER.format(LocalDate.now()), filename);

        var metadata = new ObjectMetadata();
        metadata.setContentType(file.getContentType());
        metadata.setContentDisposition(type.contentDisposition());
        oss.putObject(bucket, fullName, file.getInputStream(), metadata);
        return Joiner.on("/").join(accessDomain, fullName);
    }

    /**
     * @return 临时 token
     */
    @SneakyThrows
    public OssToken getToken(FileType type) {
        var request = new AssumeRoleRequest();
        request.setSysMethod(MethodType.POST);
        request.setRoleArn(roleArn);
        request.setRoleSessionName("sabercon");
        request.setDurationSeconds(3600L);
        var credentials = acsClient.getAcsResponse(request).getCredentials();
        return OssToken.builder().accessKeyId(credentials.getAccessKeyId())
                .accessKeySecret(credentials.getAccessKeySecret())
                .securityToken(credentials.getSecurityToken())
                .expiration(credentials.getExpiration())
                .endpoint(endpoint).bucket(bucket).accessDomain(accessDomain)
                .dir(Joiner.on("/").join(type.dir(), FORMATTER.format(LocalDate.now()))).build();
    }
}
