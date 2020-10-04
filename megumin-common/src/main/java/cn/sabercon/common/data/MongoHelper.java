package cn.sabercon.common.data;

import cn.sabercon.common.domian.PageModel;
import cn.sabercon.common.domian.PageParam;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Delegate;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

/**
 * mongo 操作协助类
 *
 * @author SaberCon
 * @since 1.0.0
 */
@Component
@RequiredArgsConstructor
public class MongoHelper {

    @Delegate
    private final MongoTemplate template;

    /**
     * 分页查询, 注意 query 查询前后不会改变且其分页属性会被忽略, 排序属性需要加在 query 上
     */
    public <T> PageModel<T> findPage(Query query, Class<T> entityClass, PageParam pageParam) {
        long total = template.count(query, entityClass);
        // 保留原来的分页参数
        long skip = query.getSkip();
        int limit = query.getLimit();
        // 加上新的分页参数
        query.skip(pageParam.calcSkip()).limit(pageParam.getPageSize());
        var list = template.find(query, entityClass);
        // 还原原来的分页参数
        query.skip(skip).limit(limit);
        return pageParam.toPageModel(list, total);
    }
}
