package cn.sabercon.main.domain.model;

import lombok.Data;

/**
 * 缓存的登录用户信息
 *
 * @author SaberCon
 * @since 1.0.0
 */
@Data
public class LoginUserInfo extends UserInfo {

    private String phone;
}
