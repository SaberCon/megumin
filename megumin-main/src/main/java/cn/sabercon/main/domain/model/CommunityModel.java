package cn.sabercon.main.domain.model;

import lombok.Data;

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

    private String desc;

    private Long memberCount;
}
