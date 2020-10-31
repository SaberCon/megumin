package cn.sabercon.main.domain.param;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 登录参数
 *
 * @author SaberCon
 * @since 1.0.0
 */
@Data
public class LoginParam {

    @NotNull
    private String phone;

    private String password;

    private String code;
}
