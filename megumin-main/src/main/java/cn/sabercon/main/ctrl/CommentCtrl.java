package cn.sabercon.main.ctrl;

import cn.sabercon.common.anno.CustomController;
import cn.sabercon.main.domain.model.CommentModel;
import cn.sabercon.main.domain.param.CommentParam;
import cn.sabercon.main.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author SaberCon
 * @since 1.0.0
 */
@CustomController("comment")
@RequiredArgsConstructor
public class CommentCtrl {

    private final CommentService service;

    @GetMapping("post")
    public Page<CommentModel> listByPostId(@NotNull Long postId) {
        return service.listByPostId(postId);
    }

    @GetMapping("quote")
    public Page<CommentModel> listByQuoteId(@NotNull Long quoteId) {
        return service.listByQuoteId(quoteId);
    }

    @GetMapping
    public CommentModel get(@NotNull Long id) {
        return service.get(id);
    }

    @PostMapping
    public void publish(@RequestBody @Valid CommentParam param) {
        service.publish(param);
    }
}
