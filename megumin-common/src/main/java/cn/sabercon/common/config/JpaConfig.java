package cn.sabercon.common.config;

import cn.sabercon.common.CommonConstant;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * 配置包扫描路径以扫描自定义的 jpa 扩展
 *
 * @author SaberCon
 * @since 1.0.0
 */
@Configuration
@EnableJpaRepositories(basePackages = CommonConstant.BASE_PACKAGE)
public class JpaConfig {
}
