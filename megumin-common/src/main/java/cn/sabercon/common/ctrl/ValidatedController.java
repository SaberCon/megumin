package cn.sabercon.common.ctrl;


import org.springframework.core.annotation.AliasFor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.*;

/**
 * @author SaberCon
 * @since 1.0.0
 */
@Validated
@Documented
@RequestMapping
@RestController
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidatedController {

    @AliasFor(annotation = RequestMapping.class)
    String value() default "";
}
