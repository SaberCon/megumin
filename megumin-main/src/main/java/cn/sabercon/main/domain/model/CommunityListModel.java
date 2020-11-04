package cn.sabercon.main.domain.model;

import lombok.Data;

/**
 * 社区列表模型
 *
 * @author SaberCon
 * @since 1.0.0
 */
@Data
public class CommunityListModel {

    private Long id;

    private String name;

    private String logo;

    private Long memberCount;
}
