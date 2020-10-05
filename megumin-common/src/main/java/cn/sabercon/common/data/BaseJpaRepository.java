package cn.sabercon.common.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * jpa 数据层通用实现接口
 *
 * @author SaberCon
 * @since 1.0.0
 */
@NoRepositoryBean
public interface BaseJpaRepository<T> extends JpaRepository<T, Long>, JpaSpecificationExecutor<T> {
}
