package cn.sabercon.main.domain.entity;

import cn.sabercon.common.domian.BaseEntity;
import cn.sabercon.common.enums.IntEnumType;
import cn.sabercon.common.enums.type.Gender;
import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

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

    @Column(nullable = false, unique = true)
    String username;

    @Column(nullable = false, unique = true)
    String phone;

    @Column(nullable = false)
    String password;

    @Column(nullable = false)
    String nickname;

    String avatar;

    String profile;

    @Type(type = IntEnumType.CLASS_FULL_NAME)
    Gender gender;

    LocalDate birthday;
}
