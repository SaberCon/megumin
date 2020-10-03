package cn.sabercon.user.ctrl;

import cn.sabercon.common.enums.type.SortEnum;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @author SaberCon
 * @since 1.0.0
 */
@Data
public class Demo {

    @NotEmpty
    private String name;

    private LocalDateTime date;

    @NotNull
    private SortEnum sort;
}
