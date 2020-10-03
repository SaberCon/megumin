package cn.sabercon.common.config;

import cn.sabercon.common.constant.CommonConstant;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.ComponentScan;

/**
 * 公共包自动装配
 *
 * @author SaberCon
 * @since 1.0.0
 */
@ComponentScan(CommonConstant.BASE_COMMON_PACKAGE)
@ConditionalOnProperty(name = "sabercon.enable-common", havingValue = "true")
public class CommonAutoConfig {
}
