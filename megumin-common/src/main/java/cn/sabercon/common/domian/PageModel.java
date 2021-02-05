package cn.sabercon.common.domian;

import lombok.Value;
import org.springframework.data.domain.Page;

import java.util.List;
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
    /**
     * 总数
     */
    long total;
    /**
     * 数据列表
     */
    List<T> list;

    /**
     * 转换 SpringData 分页后的结果
     */
    public static <T> PageModel<T> from(Page<T> page) {
        return new PageModel<>(page.getTotalElements(), page.getContent());
    }

    public <U> PageModel<U> map(Function<? super T, ? extends U> converter) {
        return new PageModel<>(total, list.stream().map(converter).collect(Collectors.toList()));
    }
}
