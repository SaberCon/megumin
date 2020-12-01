package cn.sabercon.main.domain.entity;

import cn.sabercon.common.domian.BaseEntity;
import cn.sabercon.common.enums.IntEnumType;
import cn.sabercon.main.enums.type.TextType;
import lombok.Data;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.Type;

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

    private String communityName;

    private Long createdBy;

    private Long lastRepliedBy;

    private String title;

    private String text;

    @Type(type = IntEnumType.CLASS_FULL_NAME)
    private TextType type;

    /**
     * 当前楼层最大数
     */
    private Long maxSn;

    /**
     * 评论与回复数量
     */
    private Long comments;
}