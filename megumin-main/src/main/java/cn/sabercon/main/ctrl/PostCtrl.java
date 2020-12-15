package cn.sabercon.main.ctrl;

import cn.sabercon.common.anno.CustomController;
import cn.sabercon.main.domain.model.PostModel;
import cn.sabercon.main.domain.param.PostParam;
import cn.sabercon.main.service.PostService;
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
@CustomController("post")
@RequiredArgsConstructor
public class PostCtrl {

    private final PostService service;

    @GetMapping("recent")
    public Page<PostModel> listRecent(@NotNull Long cid) {
        return service.listRecent(cid);
    }

    @GetMapping("follow")
    public Page<PostModel> listFollowed() {
        return service.listFollowed();
    }

    @GetMapping
    public PostModel get(@NotNull Long id) {
        return service.get(id);
    }

    @PostMapping("follow")
    public void follow(@NotNull Long id, @RequestParam(defaultValue = "0") Boolean un) {
        service.follow(id, un);
    }

    @PostMapping
    public void publish(@RequestBody @Valid PostParam param) {
        service.publish(param);
    }
}
