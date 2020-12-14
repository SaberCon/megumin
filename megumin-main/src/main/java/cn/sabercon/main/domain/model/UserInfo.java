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

    private String username;

    private String avatar;

    private User.Gender gender;

    private String about;

    private Long karma;

    private Long up;

    private Long down;
}
