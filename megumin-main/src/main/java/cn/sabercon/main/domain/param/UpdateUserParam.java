package cn.sabercon.main.domain.param;

import cn.sabercon.main.domain.entity.User;
import lombok.Data;

/**
 * 修改用户信息参数
 *
 * @author SaberCon
 * @since 1.0.0
 */
@Data
public class UpdateUserParam {

    private String username;

    private String avatar;

    private User.Gender gender;

    private String about;
}
