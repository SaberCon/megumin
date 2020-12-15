package cn.sabercon.main.domain.model;

import cn.sabercon.main.enums.type.ContentType;
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

    private UserInfo creator;

    private Long postId;

    private Long quoteId;

    private String text;

    private ContentType type;

    private Long replies;
}
