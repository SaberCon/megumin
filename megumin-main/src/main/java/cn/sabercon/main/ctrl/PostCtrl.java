package cn.sabercon.main.ctrl;

import cn.sabercon.common.anno.ServiceController;
import cn.sabercon.common.domian.PageModel;
import cn.sabercon.common.domian.PageQuery;
import cn.sabercon.main.domain.model.PostModel;
import cn.sabercon.main.service.PostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author SaberCon
 * @since 1.0.0
 */
@Api(tags = "帖子相关接口")
@ServiceController("post")
@RequiredArgsConstructor
public class PostCtrl {

    private final PostService service;

    @GetMapping("recent")
    @ApiModelProperty("最新更新分页查询")
    public PageModel<PostModel> listRecent(@NotNull Long communityId, @Valid PageQuery pageQuery) {
        return service.listRecent(communityId, pageQuery);
    }

    @GetMapping
    public PostModel get(@NotNull Long id) {
        return service.get(id);
    }
}
