package cn.sabercon.common.domian;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 通用的分页对象
 *
 * @author SaberCon
 * @since 1.0.0
 */
@Data
public class PageModel<T> {

    /**
     * 页码
     */
    private int page;
    /**
     * 页幅
     */
    private int size;
    /**
     * 总数
     */
    private long total;
    /**
     * 数据列表
     */
    private List<T> list;

    /**
     * 转换 SpringData 分页后的结果
     */
    public static <T> PageModel<T> from(Page<T> pageInfo) {
        var page = new PageModel<T>();
        page.setPage(pageInfo.getNumber());
        page.setSize(pageInfo.getSize());
        page.setTotal(pageInfo.getTotalElements());
        page.setList(pageInfo.getContent());
        return page;
    }

    public <U> PageModel<U> map(Function<? super T, ? extends U> converter) {
        var newPage = new PageModel<U>();
        newPage.setPage(page);
        newPage.setSize(size);
        newPage.setTotal(total);
        newPage.setList(list.stream().map(converter).collect(Collectors.toList()));
        return newPage;
    }

    public PageModel<T> foreach(Consumer<? super T> action) {
        list.forEach(action);
        return this;
    }
}
