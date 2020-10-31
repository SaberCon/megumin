package cn.sabercon.common;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

/**
 * 开启公共包自动装配, example 包下的类只是用于自动生成, 无需扫描
 *
 * @author SaberCon
 * @since 1.0.0
 */
@ComponentScan(value = CommonConstant.BASE_COMMON_PACKAGE,
        excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX, pattern = CommonConstant.BASE_COMMON_PACKAGE + ".example"))
@ConditionalOnProperty(name = "sabercon.enable-common", havingValue = "true")
public class CommonAutoConfig {
}
