package cn.sabercon.main.domain.entity;

import cn.sabercon.common.domian.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 用户加入社区表
 *
 * @author SaberCon
 * @since 1.0.0
 */
@Data
@Entity
@Table(name = "t_user_community")
public class UserCommunity extends BaseEntity {

    private Long userId;

    private Long communityId;
}
