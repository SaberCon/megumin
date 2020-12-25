package cn.sabercon.common.aspect;

import cn.hutool.core.exceptions.ExceptionUtil;
import cn.sabercon.common.domian.Result;
import cn.sabercon.common.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Collection;
import java.util.stream.Collectors;

import static cn.sabercon.common.enums.BaseCode.PARAM_WRONG;
import static cn.sabercon.common.enums.BaseCode.UNKNOWN_ERROR;

/**
 * 通用的全局异常处理器
 *
 * @author SaberCon
 * @since 1.0.0
 */
@Slf4j
@RestControllerAdvice
public class ExceptionCatchAdvice {

    @ExceptionHandler(Throwable.class)
    public Result<Void> handleException(Throwable e) {
        log.error(e.getMessage(), e);
        if (log.isDebugEnabled()) {
            return Result.fail(UNKNOWN_ERROR.code(), e.getClass().getSimpleName() + ": " + e.getLocalizedMessage(),
                    ExceptionUtil.stacktraceToString(e));
        }
        return Result.fail(UNKNOWN_ERROR.code(), e.getClass().getSimpleName() + ": " + e.getLocalizedMessage());
    }

    @ExceptionHandler(ServiceException.class)
    public Result<Void> handleServiceException(ServiceException e) {
        warn(e);
        return Result.fail(e.getCode(), e.getLocalizedMessage());
    }

    /**
     * 统一处理请求参数校验 (普通传参)
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public Result<Void> handleConstraintViolationException(ConstraintViolationException e) {
        warn(e);
        return Result.fail(PARAM_WRONG.code(), buildViolationMsg(e.getConstraintViolations()));
    }

    /**
     * 统一处理请求参数校验 (实体对象数据绑定传参)
     */
    @ExceptionHandler(BindException.class)
    public Result<Void> handleBindException(BindException e) {
        warn(e);
        return Result.fail(PARAM_WRONG.code(), buildErrorMsg(e.getBindingResult().getFieldErrors()));
    }

    /**
     * 统一处理请求参数校验 (实体对象 json 请求体传参)
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<Void> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        warn(e);
        return Result.fail(PARAM_WRONG.code(), buildErrorMsg(e.getBindingResult().getFieldErrors()));
    }

    private void warn(Exception e) {
        log.warn("catch {}: {}", e.getClass().getSimpleName(), e.getMessage());
    }

    private static String buildErrorMsg(Collection<FieldError> errors) {
        return errors.stream().map(e -> e.getField() + ":" + e.getDefaultMessage())
                .collect(Collectors.joining(","));
    }

    private static String buildViolationMsg(Collection<ConstraintViolation<?>> violations) {
        return violations.stream().map(v -> {
            var field = "";
            for (var node : v.getPropertyPath()) {
                // 取最后一个结点即为参数的名称
                field = node.getName();
            }
            return field + ":" + v.getMessage();
        }).collect(Collectors.joining(","));
    }
}
