package cn.sabercon.main.domain.param;

import cn.sabercon.main.enums.type.TextType;
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
    private String communityName;

    @NotNull
    private String title;

    @NotNull
    private String text;

    @NotNull
    private TextType type;
}
