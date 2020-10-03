package cn.sabercon.common.domian;

import lombok.Data;

import java.util.List;

/**
 * 通用的分页对象
 *
 * @author SaberCon
 * @since 1.0.0
 */
@Data
public class Page<T> {

    /**
     * 页码
     */
    private int pageNum;
    /**
     * 页幅
     */
    private int pageSize;
    /**
     * 总数
     */
    private long total;
    /**
     * 数据列表
     */
    private List<T> list;
}
