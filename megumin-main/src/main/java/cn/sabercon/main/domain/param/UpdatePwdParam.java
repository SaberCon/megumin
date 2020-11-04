package cn.sabercon.main.domain.param;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 修改密码参数
 *
 * @author SaberCon
 * @since 1.0.0
 */
@Data
public class UpdatePwdParam {

    @NotNull
    private String code;

    @NotNull
    private String newPwd;
}
