package cn.sabercon.main.domain.entity;

import cn.sabercon.common.domian.BaseEntity;
import lombok.Data;

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
public class Reply extends BaseEntity {

    /**
     * 回复者 id
     */
    private Long userId;

    private Long postId;

    private Long commentId;

    private String content;
}
