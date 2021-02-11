package cn.sabercon.common.data;

import cn.sabercon.common.domian.BaseEntity;
import cn.sabercon.common.util.HttpUtils;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * spring data 相关查询操作工具类
 *
 * @author SaberCon
 * @since 1.0.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class QueryUtils {

    public static final String PAGE_PARAM = "p";
    public static final String SIZE_PARAM = "s";
    public static final Sort ASC_CTIME = Sort.sort(BaseEntity.class).by(BaseEntity::getId).ascending();
    public static final Sort DESC_CTIME = Sort.sort(BaseEntity.class).by(BaseEntity::getId).descending();
    public static final Sort ASC_MTIME = Sort.sort(BaseEntity.class).by(BaseEntity::getMtime).ascending().and(ASC_CTIME);
    public static final Sort DESC_MTIME = Sort.sort(BaseEntity.class).by(BaseEntity::getMtime).descending().and(DESC_CTIME);

    /**
     * @return 由请求头中分页参数生成的分页请求, 分页参数不存在时会采用默认参数
     */
    public static Pageable pagination(Sort sort, int defaultPage, int defaultSize) {
        int page = HttpUtils.getParam(PAGE_PARAM).map(Integer::parseInt).filter(p -> p >= 0).orElse(defaultPage);
        int size = HttpUtils.getParam(SIZE_PARAM).map(Integer::parseInt).filter(s -> s > 0).orElse(defaultSize);
        return PageRequest.of(page, size, sort);
    }

    public static Pageable pagination(Sort sort) {
        return pagination(sort, 0, 10);
    }
}
