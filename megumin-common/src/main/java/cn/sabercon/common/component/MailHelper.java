package cn.sabercon.common.component;

import cn.hutool.core.exceptions.ExceptionUtil;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;
import java.util.Objects;

/**
 * 发送邮件帮助类
 *
 * @author SaberCon
 * @since 1.0.0
 */
@Component
@RequiredArgsConstructor
public class MailHelper {

    private final JavaMailSender jms;

    @Value("${sabercon.error-mail}")
    private String errorReceiver;

    @Value("${spring.mail.username}")
    private String from;

    /**
     * 发送异常信息到设置的接收邮箱
     */
    public void sendErrorDetail(Throwable e) {
        sendSimpleEmail(errorReceiver, e.getMessage(), ExceptionUtil.stacktraceToString(e));
    }

    /**
     * @param content 普通文本内容
     */
    public void sendSimpleEmail(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        jms.send(message);
    }

    /**
     * @param content        html 文本内容
     * @param attachmentName 附件名称, 可为 null
     * @param attachment     附件资源, 可为 null
     */
    @SneakyThrows
    public void sendHtmlEmail(String to, String subject, String content, String attachmentName, InputStreamSource attachment) {
        MimeMessage message = jms.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content, true);
        if (Objects.nonNull(attachmentName) && Objects.nonNull(attachment)) {
            helper.addAttachment(attachmentName, attachment);
        }
        jms.send(message);
    }

    @SneakyThrows
    public void sendHtmlEmail(String to, String subject, String content) {
        sendHtmlEmail(to, subject, content, null, null);
    }

}
