package cn.sabercon.main.ctrl;

import cn.sabercon.common.anno.Ctrl;
import cn.sabercon.main.domain.model.PostModel;
import cn.sabercon.main.service.PostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;

import javax.validation.constraints.NotNull;

/**
 * @author SaberCon
 * @since 1.0.0
 */
@Api(tags = "帖子相关接口")
@Ctrl("post")
@RequiredArgsConstructor
public class PostCtrl {

    private final PostService service;

    @GetMapping("recent")
    @ApiModelProperty("最新更新分页查询")
    public Page<PostModel> listRecent(@NotNull Long communityId) {
        return service.listRecent(communityId);
    }

    @GetMapping
    public PostModel get(@NotNull Long id) {
        return service.get(id);
    }
}
