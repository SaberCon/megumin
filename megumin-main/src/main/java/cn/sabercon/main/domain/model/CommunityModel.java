package cn.sabercon.main.domain.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 社区
 *
 * @author SaberCon
 * @since 1.0.0
 */
@Data
public class CommunityModel {

    private Long id;

    private String name;

    private String logo;

    private String banner;

    private String about;

    private LocalDateTime ctime;

    private Long members;

    @ApiModelProperty("当前用户是否已加入")
    private Boolean joined = false;
}
