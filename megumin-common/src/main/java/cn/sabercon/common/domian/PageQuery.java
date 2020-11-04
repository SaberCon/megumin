package cn.sabercon.common.domian;

import cn.sabercon.common.enums.type.SortType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import javax.validation.constraints.Positive;

/**
 * 通用的分页查询参数对象
 *
 * @author SaberCon
 * @implNote 多条件查询的参数对象可以继承此类以简化操作
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageQuery {

    /**
     * 页码
     */
    @Positive
    private int p = 1;
    /**
     * 页幅
     */
    @Positive
    private int s = 10;

    /**
     * @return 跳过的条目数, 用于 limit 查询
     */
    public long calcSkip() {
        return ((long) s) * (p - 1);
    }

    public PageRequest toPageRequest(Sort sort) {
        return PageRequest.of(p - 1, s, sort);
    }

    public PageRequest toPageRequest() {
        return toPageRequest(Sort.unsorted());
    }

    public PageRequest toPageRequest(SortType sort, String... properties) {
        return toPageRequest(Sort.by(sort.direction(), properties));
    }

    /**
     * 默认升序
     */
    public PageRequest toPageRequest(String... properties) {
        return toPageRequest(SortType.ASC, properties);
    }
}
