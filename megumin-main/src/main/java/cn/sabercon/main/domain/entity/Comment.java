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

    private Long createdBy;

    private Long postId;

    /**
     * 楼层数
     */
    private Integer sn;

    private String text;

    @Type(type = IntEnumType.CLASS_FULL_NAME)
    private TextType type;

    /**
     * 回复数量
     */
    private Long replies;
}
