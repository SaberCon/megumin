package cn.sabercon.main.domain.entity;

import cn.sabercon.common.domian.BaseEntity;
import cn.sabercon.main.enums.type.ContentType;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

    public static final long TOP_COMMENT = 0;

    private Long creator;

    private Long postId;

    private Long quoteId;

    private String text;

    @Enumerated(EnumType.STRING)
    private ContentType type;

    private Long replies;
}
