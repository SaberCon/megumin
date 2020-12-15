package cn.sabercon.main.domain.entity;

import cn.sabercon.common.domian.BaseEntity;
import lombok.Data;

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
public class User extends BaseEntity {

    @Column(unique = true)
    private String phone;

    @Column(unique = true)
    private String username;

    private String password;

    private String avatar;

    private String about;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    public enum Gender {
        UNKNOWN, BOY, GIRL
    }
}
