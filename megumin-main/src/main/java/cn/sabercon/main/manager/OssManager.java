package cn.sabercon.main.manager;

import cn.sabercon.main.domain.model.OssToken;
import cn.sabercon.main.enums.type.FileType;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.auth.sts.AssumeRoleRequest;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
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

    @Value("${aliyun.oss.access-key-id}")
    private String accessKeyId;
    @Value("${aliyun.oss.access-key-secret}")
    private String accessKeySecret;
    @Value("${aliyun.oss.endpoint}")
    private String endpoint;
    @Value("${aliyun.oss.bucket}")
    private String bucket;
    @Value("${aliyun.oss.access-domain}")
    private String accessDomain;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd");

    /**
     * 上传文件
     */
    @SneakyThrows
    public String upload(MultipartFile file, FileType type, String filename) {
        // 拼接文件全路径名
        var fullname = Joiner.on("/").join(type.dir(), FORMATTER.format(LocalDate.now()), filename);

        var metadata = new ObjectMetadata();
        metadata.setContentType(file.getContentType());
        metadata.setContentDisposition(type.contentDisposition());

        var ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        try {
            var result = ossClient.putObject(bucket, fullname, file.getInputStream(), metadata);
            log.debug("aliyun oss upload result: {}", result.getResponse().getContent());
        } finally {
            ossClient.shutdown();
        }
        return Joiner.on("/").join(accessDomain, fullname);
    }

    /**
     * @return 临时 token
     */
    @SneakyThrows
    public OssToken getToken(FileType type) {
//        DefaultProfile.addEndpoint("", "", "Sts", endpoint);
        var client = new DefaultAcsClient(DefaultProfile.getProfile("", accessKeyId, accessKeySecret));
        var request = new AssumeRoleRequest();
        request.setSysMethod(MethodType.POST);
//        request.setRoleArn(roleArn);
//        request.setRoleSessionName(roleSessionName);
        request.setDurationSeconds(3600L);
        var credentials = client.getAcsResponse(request).getCredentials();
        return OssToken.builder().accessKeyId(credentials.getAccessKeyId())
                .accessKeySecret(credentials.getAccessKeySecret())
                .securityToken(credentials.getSecurityToken())
                .expiration(credentials.getExpiration())
                .endpoint(endpoint).bucket(bucket).accessDomain(accessDomain)
                .dir(Joiner.on("/").join(type.dir(), FORMATTER.format(LocalDate.now()))).build();
    }
}
