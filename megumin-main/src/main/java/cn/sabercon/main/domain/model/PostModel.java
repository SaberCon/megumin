package cn.sabercon.main.domain.model;

import cn.sabercon.main.domain.dto.UserSimpleInfo;
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

    private LocalDateTime mtime;

    private UserSimpleInfo user;

    private Long communityId;

    private String title;

    private String content;

    private String link;

    private String video;

    private String imgList;

    private Long commentCount;
}
