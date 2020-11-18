package cn.sabercon.common.domian;

import io.swagger.annotations.ApiModelProperty;
import lombok.Value;
import lombok.experimental.FieldNameConstants;

import javax.validation.constraints.Positive;

/**
 * 通用的分页查询参数对象
 *
 * @author SaberCon
 * @since 1.0.0
 */
@Value
@FieldNameConstants
public class PageQuery {

    public static final int DEFAULT_PAGE = 0;

    public static final int DEFAULT_SIZE = 10;

    @Positive
    @ApiModelProperty("页码, 从 0 开始")
    int p = DEFAULT_PAGE;

    @Positive
    @ApiModelProperty("页幅")
    int s = DEFAULT_SIZE;
}
