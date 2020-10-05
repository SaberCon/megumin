package cn.sabercon.common.rest;

import cn.sabercon.common.domian.Result;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 包装返回结果的切面
 *
 * @author SaberCon
 * @since 1.0.0
 */
@ControllerAdvice
public class ResultWrapAdvice implements ResponseBodyAdvice<Object> {

    /**
     * @return 是否是处理业务请求的方法
     */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return returnType.getDeclaringClass().isAnnotationPresent(ServiceController.class);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (!MediaType.APPLICATION_JSON.isCompatibleWith(selectedContentType)) {
            // 不是 json 处理的不再包装
            return body;
        }
        if (body instanceof Result) {
            // 已经是包装结果不再包装
            return body;
        }
        return Result.ok(body);
    }
}
