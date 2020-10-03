package cn.sabercon.common.domian;

import lombok.Data;

/**
 * 通用的分页查询参数对象
 *
 * @author SaberCon
 * @since 1.0.0
 */
@Data
public class PageParam {

    /**
     * 页码
     */
    private int pageNum = 1;
    /**
     * 页幅
     */
    private int pageSize = 10;
}
