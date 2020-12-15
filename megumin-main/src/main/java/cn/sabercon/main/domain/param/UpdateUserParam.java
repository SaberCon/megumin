package cn.sabercon.main.domain.param;

import cn.sabercon.main.domain.entity.User;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 修改用户信息参数
 *
 * @author SaberCon
 * @since 1.0.0
 */
@Data
public class UpdateUserParam {

    @NotNull
    private String username;

    @NotNull
    private String avatar;

    @NotNull
    private User.Gender gender;

    private String about;
}
