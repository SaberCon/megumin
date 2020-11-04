package cn.sabercon.main.domain.model;

import cn.sabercon.main.domain.dto.UserSimpleInfo;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 回复
 *
 * @author SaberCon
 * @since 1.0.0
 */
@Data
public class ReplyModel {

    private Long id;

    private LocalDateTime ctime;

    private UserSimpleInfo user;

    private Long postId;

    private Long commentId;

    private String content;
}
