package cn.sabercon.main.manager;

import cn.hutool.core.util.RandomUtil;
import cn.sabercon.common.data.RedisHelper;
import cn.sabercon.common.util.StrUtils;
import cn.sabercon.main.enums.type.SmsType;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 短信相关操作类
 *
 * @author SaberCon
 * @since 1.0.0
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class SmsManager {

    private final RedisHelper redisHelper;

    @Value("${aliyun.access-key-id}")
    private String accessKeyId;
    @Value("${aliyun.access-key-secret}")
    private String accessKeySecret;
    @Value("${aliyun.sms.sign-name}")
    private String signName;
    @Value("${aliyun.sms.template-code}")
    private String templateCode;

    /**
     * 保存到 redis 的验证码的键名前缀
     */
    private static String SMS_CODE_PREFIX = "sms:code";

    /**
     * 发送短信验证码
     */
    public void sendCode(SmsType type, String phone) {
        var code = RandomUtil.randomNumbers(4);

        var client = new DefaultAcsClient(DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret));
        var request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", signName);
        request.putQueryParameter("TemplateCode", templateCode);
        request.putQueryParameter("TemplateParam", "{\"code\":\"" + code + "\"}");
        try {
            var response = client.getCommonResponse(request);
            log.debug("aliyun sms code sending result: {}", response.getData());
        } catch (ClientException e) {
            log.error("aliyun sms code sending failed", e);
            return;
        }

        // 保存验证码到redis中，保存时间五分钟
        var key = StrUtils.buildRedisKey(SMS_CODE_PREFIX, type.val(), phone);
        redisHelper.set(key, code, 5, TimeUnit.MINUTES);
        log.debug("set sms code to redis, phone number:{}, code:{}", phone, code);
    }

    /**
     * @return 短信验证码是否正确
     */
    public boolean checkCode(SmsType type, String phone, String code) {
        var key = StrUtils.buildRedisKey(SMS_CODE_PREFIX, type.val(), phone);
        return code.equals(redisHelper.get(key));
    }
}
