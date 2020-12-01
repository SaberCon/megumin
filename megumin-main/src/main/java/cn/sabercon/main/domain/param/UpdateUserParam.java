package cn.sabercon.main.domain.param;

import cn.sabercon.common.enums.type.Gender;
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

    private Gender gender;

    private String about;
}
