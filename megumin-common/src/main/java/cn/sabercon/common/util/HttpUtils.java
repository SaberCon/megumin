package cn.sabercon.common.util;

import cn.hutool.core.util.NumberUtil;
import cn.sabercon.common.domian.PageQuery;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
 * request response 相关操作工具类
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

    public static Optional<String> getParam(String key) {
        return Optional.ofNullable(getRequest().getParameter(key));
    }

    public static Optional<Long> getLongParam(String key) {
        return Optional.ofNullable(getRequest().getParameter(key)).map(p -> {
            try {
                return Long.parseLong(p);
            } catch (NumberFormatException e) {
                return null;
            }
        });
    }

    public static Optional<Integer> getIntParam(String key) {
        return Optional.ofNullable(getRequest().getParameter(key)).map(p -> {
            try {
                return Integer.parseInt(p);
            } catch (NumberFormatException e) {
                return null;
            }
        });
    }

    /**
     * @return 由请求头中分页参数生成的分页请求, 分页参数不存在时会采用默认参数
     */
    public static Pageable pageable(Sort sort) {
        int page = getIntParam(PageQuery.Fields.p).filter(p -> p >= 0).orElse(PageQuery.DEFAULT_PAGE);
        int size = getIntParam(PageQuery.Fields.s).filter(s -> s > 0).orElse(PageQuery.DEFAULT_SIZE);
        return PageRequest.of(page, size, sort);
    }

    /**
     * @param properties 要排序的字段
     * @return 升序查询的分页请求
     */
    public static Pageable ascPageable(String... properties) {
        return pageable(Sort.by(Sort.Direction.ASC, properties));
    }

    /**
     * @param properties 要排序的字段
     * @return 降序查询的分页请求
     */
    public static Pageable descPageable(String... properties) {
        return pageable(Sort.by(Sort.Direction.DESC, properties));
    }

    public static Pageable pageable() {
        return pageable(Sort.unsorted());
    }

    /**
     * @return token 中的用户 id, 没有时返回 null
     */
    public static Long userId() {
        var tokenOpt = getHeader(TOKEN_HEADER);
        if (ContextHolder.isNotProd() && tokenOpt.stream().anyMatch(NumberUtil::isLong)) {
            // 非生产环境 token 直接为用户 id 时可通过
            return tokenOpt.map(Long::parseLong).get();
        }
        return tokenOpt.map(Jwt::getIdFromToken).orElse(null);
    }

    /**
     * @return token 中的用户 id, 没有时抛出异常
     */
    @NotNull
    public static Long userIdOrUnAuth() {
        return Assert.notNull(userId(), UNAUTHORIZED);
    }
}
