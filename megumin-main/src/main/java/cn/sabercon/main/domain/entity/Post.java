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

    private Long creator;

    private Long communityId;

    private String title;

    private String text;

    @Enumerated(EnumType.STRING)
    private ContentType type;

    private Long replies;
}