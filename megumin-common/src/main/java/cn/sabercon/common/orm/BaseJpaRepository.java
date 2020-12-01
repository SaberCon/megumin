package cn.sabercon.common.orm;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
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
     */
    @Modifying
    @Query("update #{#entityName} set #{field} = #{field} + 1 where id = #{id}")
    void increment(Long id, String field);
}
