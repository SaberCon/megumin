package cn.sabercon.main.domain.model;

import cn.sabercon.main.domain.dto.UserSimpleInfo;
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

    private UserSimpleInfo user;

    private Long postId;

    private Long level;

    private String content;

    private String imgList;

    private Long replyCount;
}
