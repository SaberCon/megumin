package cn.sabercon.common.jpa;

import cn.sabercon.common.CommonConstant;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author SaberCon
 * @since 1.0.0
 */
@Configuration
@EnableJpaRepositories(basePackages = CommonConstant.BASE_PACKAGE, repositoryBaseClass = BaseJpaRepositoryImpl.class)
public class JpaConfig {
}
