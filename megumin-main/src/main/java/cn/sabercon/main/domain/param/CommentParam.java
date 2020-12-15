package cn.sabercon.main.domain.param;

import cn.sabercon.main.domain.entity.Comment;
import cn.sabercon.main.enums.type.ContentType;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 评论
 *
 * @author SaberCon
 * @since 1.0.0
 */
@Data
public class CommentParam {

    @NotNull
    private Long postId;

    private Long quoteId = Comment.TOP_COMMENT;

    @NotNull
    private String text;

    @NotNull
    private ContentType type;
}
