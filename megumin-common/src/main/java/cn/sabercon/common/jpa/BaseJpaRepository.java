package cn.sabercon.common.jpa;

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

    /**
     * 对实体的某一个字段进行自增一操作
     *
     * @param id    实体 id
     * @param field 要增加的字段, 类型为 int 或 long
     * @return 更新成功的行数
     */
    int increment(Long id, String field);
}
