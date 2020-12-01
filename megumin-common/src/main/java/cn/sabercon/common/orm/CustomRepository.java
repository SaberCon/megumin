package cn.sabercon.common.orm;

/**
 * 对 jpa 进行功能扩展
 *
 * @author SaberCon
 * @since 1.0.0
 */
public interface CustomRepository<T> {

    /**
     * 对实体的某一个字段进行自增一操作
     *
     * @param id    实体 id
     * @param field 要增加的字段, 类型为 int 或 long
     */
    void increment(Long id, String field);
}
