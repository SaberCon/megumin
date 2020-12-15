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

    @NotNull
    private String code;

    @NotNull
    private Type type;

    public enum Type {
        PWD, SMS
    }
}
