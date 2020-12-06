package cn.sabercon.main.advice;

import cn.sabercon.common.data.RedisHelper;
import cn.sabercon.common.util.Assert;
import cn.sabercon.common.util.ContextHolder;
import cn.sabercon.common.util.HttpUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static cn.sabercon.common.enums.CommonCode.UNAUTHORIZED;
import static cn.sabercon.common.util.StrUtils.buildRedisKey;
import static cn.sabercon.main.constant.RedisConstant.LOGIN_USER_PREFIX;

/**
 * 登录拦截的过滤器
 *
 * @author SaberCon
 * @since 1.0.0
 */
@Component
@RequiredArgsConstructor
public class LoginInterceptor implements HandlerInterceptor {

    private final RedisHelper redisHelper;

    /**
     * 校验用户是否登录
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 非正式环境不校验
        if (ContextHolder.isNotProd()) {
            return true;
        }
        // 跨域预请求放行
        if (HttpMethod.OPTIONS.matches(request.getMethod())) {
            return true;
        }
        var userId = Assert.notNull(HttpUtils.userId(), UNAUTHORIZED);
        Assert.isTrue(redisHelper.exists(buildRedisKey(LOGIN_USER_PREFIX, userId)), UNAUTHORIZED);
        return true;
    }
}
