package cn.sabercon.common.domian;

import cn.sabercon.common.enums.type.SortEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import javax.validation.constraints.Positive;
import java.util.List;

/**
 * 通用的分页查询参数对象
 *
 * @author SaberCon
 * @implNote 多条件查询的参数对象可以继承此类已简化操作
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageParam {

    /**
     * 页码
     */
    @Positive
    private int pageNum = 1;
    /**
     * 页幅
     */
    @Positive
    private int pageSize = 10;

    /**
     * @return 跳过的条目数, 用于 limit 查询
     */
    public long calcSkip() {
        return ((long) pageSize) * (pageNum - 1);
    }

    public PageRequest toPageRequest() {
        return PageRequest.of(pageNum - 1, pageSize);
    }

    public PageRequest toPageRequest(Sort sort) {
        return PageRequest.of(pageNum - 1, pageSize, sort);
    }

    public PageRequest toPageRequest(SortEnum sort, String... properties) {
        return PageRequest.of(pageNum - 1, pageSize, sort.direction(), properties);
    }

    public PageRequest toPageRequest(String... properties) {
        return PageRequest.of(pageNum - 1, pageSize, SortEnum.ASC.direction(), properties);
    }

    public <T> PageModel<T> toPageModel(List<T> list, long total) {
        var page = new PageModel<T>();
        page.setPageNum(pageNum);
        page.setPageSize(pageSize);
        page.setList(list);
        page.setTotal(total);
        return page;
    }
}
