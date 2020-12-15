package cn.sabercon.main.domain.entity;

import cn.sabercon.common.domian.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 用户关注帖子表
 *
 * @author SaberCon
 * @since 1.0.0
 */
@Data
@Entity
@Table(name = "t_user_post")
public class UserPost extends BaseEntity {

    private Long userId;

    private Long postId;
}