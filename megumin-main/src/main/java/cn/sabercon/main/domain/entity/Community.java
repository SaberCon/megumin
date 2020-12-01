package cn.sabercon.main.domain.entity;

import cn.sabercon.common.domian.BaseEntity;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 社区
 *
 * @author SaberCon
 * @since 1.0.0
 */
@Data
@Entity
@Table(name = "t_community")
@FieldNameConstants
public class Community extends BaseEntity {

    @Column(unique = true)
    private String name;

    private String logo;

    private String banner;

    private String desc;

    /**
     * 关注人数
     */
    private Long members;
}
