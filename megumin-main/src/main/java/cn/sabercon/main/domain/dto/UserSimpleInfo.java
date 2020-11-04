package cn.sabercon.main.domain.dto;

import lombok.Data;

/**
 * 用户简单信息
 *
 * @author SaberCon
 * @since 1.0.0
 */
@Data
public class UserSimpleInfo {

    private Long id;

    private String username;

    private String avatar;
}
