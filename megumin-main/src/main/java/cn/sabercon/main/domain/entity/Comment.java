package cn.sabercon.main.domain.entity;

import cn.sabercon.common.domian.BaseEntity;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 评论
 *
 * @author SaberCon
 * @since 1.0.0
 */
@Data
@Entity
@Table(name = "t_comment")
@FieldNameConstants
public class Comment extends BaseEntity {

    /**
     * 评论者 id
     */
    private Long userId;

    private Long postId;

    /**
     * 楼层数
     */
    private Long level;

    private String content;

    /**
     * 图片列表, 逗号分隔
     */
    private String imgList;

    private Long replyCount;
}
