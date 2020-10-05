package cn.sabercon.common.util;

import cn.sabercon.common.exception.Asserts;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

/**
 * http 相关操作工具类
 *
 * @author SaberCon
 * @since 1.0.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HttpUtils {

    public static HttpServletRequest getRequest() {
        var attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return Asserts.notNull(attributes.getRequest());
    }

    public static HttpServletResponse getResponse() {
        var attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return Asserts.notNull(attributes.getResponse());
    }

    public static void addCookie(Cookie cookie) {
        getResponse().addCookie(cookie);
    }

    public static Optional<String> getCookie(String key) {
        var cookies = getRequest().getCookies();
        if (Objects.isNull(cookies)) {
            return Optional.empty();
        }
        return Arrays.stream(cookies).filter(cookie -> Objects.equals(cookie.getName(), key))
                .findFirst().map(Cookie::getValue);
    }

    public static void addHeader(String key, String value) {
        getResponse().setHeader(key, value);
    }

    public static Optional<String> getHeader(String key) {
        return Optional.ofNullable(getRequest().getHeader(key));
    }
}
