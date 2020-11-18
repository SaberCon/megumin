package cn.sabercon.common.anno;


import org.springframework.core.annotation.AliasFor;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.*;

/**
 * 事务切面注解
 *
 * @author SaberCon
 * @since 1.0.0
 */
@Documented
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Transactional(rollbackFor = Exception.class)
public @interface Tx {

    @AliasFor(annotation = Transactional.class)
    String value() default "";
}
