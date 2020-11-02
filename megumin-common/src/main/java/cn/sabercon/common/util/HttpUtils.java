package cn.sabercon.common.util;

import cn.hutool.core.util.NumberUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

import static cn.sabercon.common.enums.CommonCode.UNAUTHORIZED;

/**
 * http 相关操作工具类
 *
 * @author SaberCon
 * @since 1.0.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HttpUtils {

    private static final String TOKEN_HEADER = "jwt-token";

    public static HttpServletRequest getRequest() {
        var attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return Assert.notNull(attributes.getRequest());
    }

    public static HttpServletResponse getResponse() {
        var attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return Assert.notNull(attributes.getResponse());
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

    /**
     * @return token 中的用户 id, 没有时返回 null
     */
    public static Long getUserId() {
        var tokenOpt = getHeader(TOKEN_HEADER);
        if (Env.isNotProd() && tokenOpt.stream().anyMatch(NumberUtil::isLong)) {
            // 非生产环境 token 直接为用户 id 时可通过
            return tokenOpt.map(Long::parseLong).get();
        }
        return tokenOpt.map(Jwt::getIdFromToken).orElse(null);
    }

    /**
     * @return token 中的用户 id, 没有时抛出异常
     */
    @NotNull
    public static Long getUserIdOrError() {
        return Assert.notNull(getUserId(), UNAUTHORIZED);
    }
}
