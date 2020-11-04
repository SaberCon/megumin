package cn.sabercon.main.domain.param;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 换绑手机参数
 *
 * @author SaberCon
 * @since 1.0.0
 */
@Data
public class UpdatePhoneParam {

    @NotNull
    private String newPhone;

    @NotNull
    private String unbindCode;

    @NotNull
    private String bindCode;
}
