package cn.sabercon.main.domain.entity;

import cn.sabercon.common.domian.BaseEntity;
import cn.sabercon.common.enums.IntEnumType;
import cn.sabercon.common.enums.type.Gender;
import lombok.Data;
import lombok.experimental.FieldNameConstants;
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
@FieldNameConstants
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
    private String about;

    /**
     * 经验值
     */
    private Long karma;

    /**
     * 点赞数
     */
    private Long up;

    /**
     * 拉黑数
     */
    private Long down;
}
