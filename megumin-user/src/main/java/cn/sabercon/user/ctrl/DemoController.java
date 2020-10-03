package cn.sabercon.user.ctrl;

import cn.sabercon.common.ctrl.ValidatedController;
import cn.sabercon.common.domian.Result;
import cn.sabercon.common.enums.type.SortEnum;
import org.springframework.web.bind.annotation.GetMapping;

import javax.validation.Valid;

/**
 * @author SaberCon
 * @since 1.0.0
 */
@ValidatedController("test")
public class DemoController {

    @GetMapping
    public Result<Demo> test(@Valid Demo demo) {
        return Result.ok(demo);
    }

    @GetMapping("1")
    public Result<SortEnum> test(SortEnum sortEnum) {
        return Result.ok(sortEnum);
    }
}
