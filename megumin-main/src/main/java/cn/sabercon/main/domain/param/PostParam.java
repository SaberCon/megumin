package cn.sabercon.main.domain.param;

import cn.sabercon.main.enums.type.ContentType;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 帖子
 *
 * @author SaberCon
 * @since 1.0.0
 */
@Data
public class PostParam {

    @NotNull
    private Long communityId;

    @NotNull
    private String title;

    @NotNull
    private String text;

    @NotNull
    private ContentType type;
}
