package cn.sabercon.main.domain.entity;

import cn.sabercon.common.domian.BaseEntity;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;

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

    @Enumerated(EnumType.STRING)
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

    public enum Gender {
        UNKNOWN, BOY, GIRL
    }
}
