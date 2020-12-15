package cn.sabercon.main.domain.model;

import cn.sabercon.main.domain.entity.User;
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

    private String phone;

    private String username;

    private String avatar;

    private String about;

    private User.Gender gender;
}
