package cn.sabercon.common.domian;

import lombok.Value;
import org.springframework.data.domain.Page;

import java.util.Collections;
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
@Value
public class PageModel<T> {

    private static final PageModel<?> EMPTY_PAGE = new PageModel<>(0, Collections.EMPTY_LIST);
    /**
     * 总数
     */
    long total;
    /**
     * 数据列表
     */
    List<T> list;

    /**
     * @return 不可变的空页对象
     */
    @SuppressWarnings("unchecked")
    public static <T> PageModel<T> emptyPage() {
        return (PageModel<T>) EMPTY_PAGE;
    }

    /**
     * 转换 SpringData 分页后的结果
     */
    public static <T> PageModel<T> from(Page<T> pageInfo) {
        return new PageModel<>(pageInfo.getTotalElements(), pageInfo.getContent());
    }

    public <U> PageModel<U> map(Function<? super T, ? extends U> converter) {
        return new PageModel<>(total, list.stream().map(converter).collect(Collectors.toList()));
    }

    public PageModel<T> foreach(Consumer<? super T> action) {
        list.forEach(action);
        return this;
    }
}
