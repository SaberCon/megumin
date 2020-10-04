package cn.sabercon.user.ctrl;

import cn.sabercon.common.data.IntEnumType;
import cn.sabercon.common.enums.type.SortEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;

/**
 * @author SaberCon
 * @since 1.0.0
 */
@Data
@Entity
public class Demo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty("名称")
    private String name;

    @PastOrPresent
    @ApiModelProperty("日期")
    private LocalDateTime date;

    @NotNull
    @Type(type = IntEnumType.CLASS_FULL_NAME)
    @ApiModelProperty("排序")
    private SortEnum sort;
}
