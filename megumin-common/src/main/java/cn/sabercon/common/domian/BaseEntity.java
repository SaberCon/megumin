package cn.sabercon.common.domian;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 基础的实体抽象类
 *
 * @author SaberCon
 * @since 1.0.0
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {

    /**
     * 主键 id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 创建时间
     */
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime ctime;

    /**
     * 更新时间
     */
    @UpdateTimestamp
    private LocalDateTime mtime;
}
