package cn.sabercon.main.domain.param;

import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty("验证码或密码")
    private String code;

    @NotNull
    private Type type;

    public enum Type {
        PWD, SMS
    }
}
