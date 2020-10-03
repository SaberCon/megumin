package cn.sabercon.common.exception;

import cn.sabercon.common.domian.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

/**
 * 通用的全局异常处理器
 *
 * @author SaberCon
 * @since 1.0.0
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public Result<Void> handleException(Exception e) {
        log.error(e.getMessage(), e);
        return Result.fail(e.getClass().getSimpleName() + ": " + e.getLocalizedMessage());
    }

    @ExceptionHandler(value = ServiceException.class)
    public Result<Void> handleServiceException(ServiceException e) {
        log.warn("catch ServiceException: {}", e.getMessage());
        return Result.fail(e.getCode(), e.getLocalizedMessage());
    }

    /**
     * 统一处理请求参数校验 (普通传参)
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    public Result<Void> handleConstraintViolationException(ConstraintViolationException e) {
        log.warn("catch ConstraintViolationException: {}", e.getMessage());
        return Result.fail(e.getConstraintViolations().stream()
                .map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
                .collect(Collectors.joining(",")));
    }

    /**
     * 统一处理请求参数校验 (实体对象数据绑定传参)
     */
    @ExceptionHandler(BindException.class)
    public Result<Void> handleBindException(BindException e) {
        log.warn("catch BindException: {}", e.getMessage());
        return Result.fail(e.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(",")));
    }

    /**
     * 统一处理请求参数校验 (实体对象请求体传参)
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<Void> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.warn("catch MethodArgumentNotValidException: {}", e.getMessage());
        return Result.fail(e.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(",")));
    }
}
