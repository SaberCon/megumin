package cn.sabercon.user.ctrl;

import cn.sabercon.common.ctrl.ValidatedController;
import cn.sabercon.common.domian.Result;
import cn.sabercon.common.enums.type.SortEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * @author SaberCon
 * @since 1.0.0
 */
@Api(tags = "测试类")
@ValidatedController("test")
public class DemoController {

    @GetMapping("1")
    public Result<SortEnum> test1(@ApiParam("排序") SortEnum sort) {
        return Result.ok(sort);
    }

    @GetMapping("2")
    @ApiOperation("测试")
    public Result<Demo> test2(@Valid Demo demo) {
        return Result.ok(demo);
    }

    @PostMapping("3")
    @ApiOperation("测试")
    public Result<Demo> test3(@Valid @RequestBody Demo demo) {
        return Result.ok(demo);
    }
}
