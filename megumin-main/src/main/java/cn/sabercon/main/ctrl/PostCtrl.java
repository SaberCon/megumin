package cn.sabercon.main.ctrl;

import cn.sabercon.common.anno.ServiceController;
import cn.sabercon.main.service.PostService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

/**
 * @author SaberCon
 * @since 1.0.0
 */
@Api(tags = "帖子相关接口")
@ServiceController("post")
@RequiredArgsConstructor
public class PostCtrl {

    private final PostService service;
}
