package cn.sabercon.user.ctrl;

import cn.sabercon.common.enums.type.SortEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;

/**
 * @author SaberCon
 * @since 1.0.0
 */
@Data
public class Demo {

    @ApiModelProperty("名称")
    private String name;

    @PastOrPresent
    @ApiModelProperty("日期")
    private LocalDateTime date;

    @NotNull
    @ApiModelProperty("排序")
    private SortEnum sort;
}
