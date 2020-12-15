package cn.sabercon.main.domain.model;

import cn.sabercon.main.enums.type.ContentType;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 帖子
 *
 * @author SaberCon
 * @since 1.0.0
 */
@Data
public class PostModel {

    private Long id;

    private LocalDateTime ctime;

    private UserInfo creator;

    private Long communityId;

    private String title;

    private String text;

    private ContentType type;

    private Long replies;
}
