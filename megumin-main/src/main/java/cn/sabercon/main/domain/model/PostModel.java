package cn.sabercon.main.domain.model;

import cn.sabercon.main.domain.dto.UserSimpleInfo;
import cn.sabercon.main.enums.type.TextType;
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

    private UserSimpleInfo createdBy;

    private String communityName;

    private String title;

    private String text;

    private TextType type;

    private Long comments;
}
