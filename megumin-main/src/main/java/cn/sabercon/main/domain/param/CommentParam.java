package cn.sabercon.main.domain.param;

import cn.sabercon.main.enums.type.TextType;
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

    @NotNull
    private String text;

    @NotNull
    private TextType type;
}
