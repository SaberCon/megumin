package cn.sabercon.main.domain.entity;

import cn.sabercon.common.domian.BaseEntity;
import cn.sabercon.common.enums.IntEnumType;
import cn.sabercon.common.enums.type.Gender;
import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 用户
 *
 * @author SaberCon
 * @since 1.0.0
 */
@Data
@Entity
@Table(name = "t_user")
public class User extends BaseEntity {

    @Column(unique = true)
    private String phone;

    @Column(unique = true)
    private String username;

    private String password;

    private String avatar;

    @Type(type = IntEnumType.CLASS_FULL_NAME)
    private Gender gender;

    /**
     * 个人简介
     */
    private String profile;
}
