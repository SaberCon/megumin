package cn.sabercon.common.domian;

import lombok.Data;
import lombok.experimental.FieldNameConstants;

import javax.validation.constraints.Positive;

/**
 * 通用的分页查询参数对象
 *
 * @author SaberCon
 * @since 1.0.0
 */
@Data
@FieldNameConstants
public class PageQuery {

    public static final int DEFAULT_PAGE = 0;

    public static final int DEFAULT_SIZE = 10;

    /**
     * 页码, 从 0 开始
     */
    @Positive
    private int p = DEFAULT_PAGE;
    /**
     * 页幅
     */
    @Positive
    private int s = DEFAULT_SIZE;
}
