package cn.sabercon.common.jpa;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.function.Function;

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
     * @param id       实体 id
     * @param property 要增加的字段
     * @param inc      要增加的数值
     * @return 更新成功的行数
     */
    int inc(Long id, Function<T, ?> property, Integer inc);

    /**
     * 添加或取消实体联系, 用于中间表
     *
     * @param entity 要添加或取消的关系
     * @param undo   是否取消
     * @return 是否操作成功
     */
    boolean addRelation(T entity, boolean undo);

    /**
     * @return 根据 lambda 表达式获取属性名称
     */
    String getPropName(Function<T, ?> property);

    /**
     * @return 根据 lambda 表达式获取排序
     */
    Sort getSort(Function<T, ?> property);
}
