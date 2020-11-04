package cn.sabercon.main.domain.model;

import cn.sabercon.common.enums.type.Gender;
import lombok.Data;

/**
 * 缓存的登录用户信息
 *
 * @author SaberCon
 * @since 1.0.0
 */
@Data
public class LoginUserInfo {

    private String phone;

    private String username;

    private String avatar;

    private Gender gender;

    private String profile;
}
