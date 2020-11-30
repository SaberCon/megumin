package cn.sabercon.common.util;

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
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

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

    /**
     * @return 由请求头中分页参数生成的分页请求, 分页参数不存在时会采用默认参数
     */
    public static Pageable pageable(Sort sort, int defaultPage, int defaultSize) {
        int page = getParam(PageQuery.Fields.p).map(Integer::parseInt).filter(p -> p >= 0).orElse(defaultPage);
        int size = getParam(PageQuery.Fields.s).map(Integer::parseInt).filter(s -> s > 0).orElse(defaultSize);
        return PageRequest.of(page, size, sort);
    }

    public static Pageable pageable(Sort sort) {
        return pageable(sort, PageQuery.DEFAULT_PAGE, PageQuery.DEFAULT_SIZE);
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

    /**
     * @return token 中的用户 id, 没有时返回 null
     */
    public static Long userId() {
        var tokenOpt = getHeader(TOKEN_HEADER);
        if (ContextHolder.isNotProd() && tokenOpt.isPresent()) {
            // 非生产环境 token 直接为用户 id 时可通过
            try {
                return Long.parseLong(tokenOpt.get());
            } catch (NumberFormatException ignored) {
            }
        }
        return tokenOpt.map(Jwt::getIdFromToken).orElse(null);
    }

}
