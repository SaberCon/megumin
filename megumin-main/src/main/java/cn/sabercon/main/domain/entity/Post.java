package cn.sabercon.main.domain.entity;

import cn.sabercon.common.domian.BaseEntity;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 帖子
 *
 * @author SaberCon
 * @since 1.0.0
 */
@Data
@Entity
@Table(name = "t_post")
@FieldNameConstants
public class Post extends BaseEntity {

    /**
     * 楼主 id
     */
    private Long userId;

    private Long communityId;

    private String title;

    private String content;

    /**
     * 网页链接
     */
    private String link;

    /**
     * 视频链接
     */
    private String video;

    /**
     * 图片列表, 逗号分隔
     */
    private String imgList;

    private Long commentCount;
}
