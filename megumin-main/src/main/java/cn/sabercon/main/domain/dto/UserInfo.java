package cn.sabercon.main.domain.dto;

import cn.sabercon.common.enums.type.Gender;
import lombok.Data;

/**
 * 缓存的登录用户信息
 *
 * @author SaberCon
 * @since 1.0.0
 */
@Data
public class UserInfo {

    private String phone;

    private String username;

    private String avatar;

    private String profile;

    private Gender gender;
}
