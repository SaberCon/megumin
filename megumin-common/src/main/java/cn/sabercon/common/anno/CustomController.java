package cn.sabercon.common.anno;


import cn.sabercon.common.aspect.ResultWrapAdvice;
import cn.sabercon.common.domian.Result;
import org.springframework.core.annotation.AliasFor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.*;

/**
 * 集成 controller 注解, 默认开启参数校验
 * 此注解的返回值会被包装成 {@link Result}
 *
 * @author SaberCon
 * @see ResultWrapAdvice
 * @since 1.0.0
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Validated
@RequestMapping
@RestController
public @interface CustomController {

    @AliasFor(annotation = RequestMapping.class)
    String[] value() default {};
}
