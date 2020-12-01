package cn.sabercon.main.ctrl;

import cn.sabercon.common.anno.CommonController;
import cn.sabercon.main.domain.model.ReplyModel;
import cn.sabercon.main.domain.param.ReplyParam;
import cn.sabercon.main.service.ReplyService;
import io.swagger.annotations.Api;
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
@Api(tags = "回复相关接口")
@CommonController("reply")
@RequiredArgsConstructor
public class ReplyCtrl {

    private final ReplyService service;

    @GetMapping("list")
    public Page<ReplyModel> list(@NotNull Long commentId) {
        return service.list(commentId);
    }

    @GetMapping
    public ReplyModel get(@NotNull Long id) {
        return service.get(id);
    }

    @PostMapping
    public void publish(@RequestBody @Valid ReplyParam param) {
        service.publish(param);
    }
}
