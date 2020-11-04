package cn.sabercon.main.ctrl;

import cn.sabercon.common.anno.ServiceController;
import cn.sabercon.common.domian.PageModel;
import cn.sabercon.common.domian.PageQuery;
import cn.sabercon.main.domain.model.CommentModel;
import cn.sabercon.main.service.CommentService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author SaberCon
 * @since 1.0.0
 */
@Api(tags = "评论相关接口")
@ServiceController("comment")
@RequiredArgsConstructor
public class CommentCtrl {

    private final CommentService service;

    @GetMapping("list")
    public PageModel<CommentModel> list(@NotNull Long postId, @Valid PageQuery pageQuery) {
        return service.list(postId, pageQuery);
    }

    @GetMapping
    public CommentModel get(@NotNull Long id) {
        return service.get(id);
    }
}
