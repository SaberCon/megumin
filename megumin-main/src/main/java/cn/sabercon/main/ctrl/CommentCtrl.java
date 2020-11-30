package cn.sabercon.main.ctrl;

import cn.sabercon.common.anno.CommonController;
import cn.sabercon.main.domain.model.CommentModel;
import cn.sabercon.main.service.CommentService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;

import javax.validation.constraints.NotNull;

/**
 * @author SaberCon
 * @since 1.0.0
 */
@Api(tags = "评论相关接口")
@CommonController("comment")
@RequiredArgsConstructor
public class CommentCtrl {

    private final CommentService service;

    @GetMapping("list")
    public Page<CommentModel> list(@NotNull Long postId) {
        return service.list(postId);
    }

    @GetMapping
    public CommentModel get(@NotNull Long id) {
        return service.get(id);
    }
}
