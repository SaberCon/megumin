package cn.sabercon.main.domain.model;

import cn.sabercon.common.enums.type.Gender;
import lombok.Data;

/**
 * 用户信息
 *
 * @author SaberCon
 * @since 1.0.0
 */
@Data
public class UserInfo {

    private Long id;

    private String username;

    private String avatar;

    private Gender gender;

    private String profile;
}
