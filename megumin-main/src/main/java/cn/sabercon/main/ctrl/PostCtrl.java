package cn.sabercon.main.ctrl;

import cn.sabercon.common.anno.CommonController;
import cn.sabercon.main.domain.model.PostModel;
import cn.sabercon.main.domain.param.PostParam;
import cn.sabercon.main.service.PostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author SaberCon
 * @since 1.0.0
 */
@Api(tags = "帖子相关接口")
@CommonController("post")
@RequiredArgsConstructor
public class PostCtrl {

    private final PostService service;

    @GetMapping("recent")
    @ApiModelProperty("最新更新分页查询")
    public Page<PostModel> listRecent(@NotNull String cname) {
        return service.listRecent(cname);
    }

    @GetMapping("follow")
    @ApiModelProperty("已关注分页查询")
    public Page<PostModel> listFollowed() {
        return service.listFollowed();
    }

    @GetMapping
    public PostModel get(@NotNull Long id) {
        return service.get(id);
    }

    @PostMapping("follow")
    public void follow(@RequestParam Long id, @ApiParam("是否取消") @RequestParam(defaultValue = "0") Boolean un) {
        service.follow(id, un);
    }

    @PostMapping
    public void publish(@RequestBody @Valid PostParam param) {
        service.publish(param);
    }
}
