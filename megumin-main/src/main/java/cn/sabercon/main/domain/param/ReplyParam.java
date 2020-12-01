package cn.sabercon.main.domain.param;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 回复
 *
 * @author SaberCon
 * @since 1.0.0
 */
@Data
public class ReplyParam {

    @NotNull
    private Long commentId;

    @NotNull
    private String text;

    private Long repliedUser;
}
