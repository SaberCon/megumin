package cn.sabercon.main.domain.model;

import cn.sabercon.main.domain.dto.UserSimpleInfo;
import cn.sabercon.main.enums.type.TextType;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 评论
 *
 * @author SaberCon
 * @since 1.0.0
 */
@Data
public class CommentModel {

    private Long id;

    private LocalDateTime ctime;

    private UserSimpleInfo createdBy;

    private Long postId;

    private Integer sn;

    private String text;

    private TextType type;

    private Long replies;
}
