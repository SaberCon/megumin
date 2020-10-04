package cn.sabercon.user.ctrl;

import cn.sabercon.common.ctrl.ValidatedController;
import cn.sabercon.common.data.RedisHelper;
import cn.sabercon.common.domian.Result;
import cn.sabercon.common.enums.type.SortEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author SaberCon
 * @since 1.0.0
 */
@Api(tags = "测试类")
@RequiredArgsConstructor
@ValidatedController("test")
public class DemoController {

    private final RedisHelper redisHelper;

    private final StringRedisTemplate redisTemplate;

    @GetMapping
    public Result<Void> test() {
        return Result.ok();
    }

    @GetMapping("1")
    public Result<SortEnum> test1(@ApiParam("排序") @NotNull SortEnum sort) {
        return Result.ok(sort);
    }

    @GetMapping("2")
    public Result<Demo> test2(@Valid Demo demo) {
        return Result.ok(demo);
    }

    @PostMapping("3")
    @ApiOperation("测试")
    public Result<Demo> test3(@RequestBody @Valid Demo demo) {
        return Result.ok(demo);
    }
}
