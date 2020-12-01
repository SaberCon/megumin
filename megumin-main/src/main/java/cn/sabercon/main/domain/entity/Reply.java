package cn.sabercon.main.domain.entity;

import cn.sabercon.common.domian.BaseEntity;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 回复
 *
 * @author SaberCon
 * @since 1.0.0
 */
@Data
@Entity
@Table(name = "t_reply")
@FieldNameConstants
public class Reply extends BaseEntity {

    private Long createdBy;

    private Long postId;

    private Long commentId;

    private String text;

    /**
     * 被回复的用户
     */
    private Long repliedUser;
}
