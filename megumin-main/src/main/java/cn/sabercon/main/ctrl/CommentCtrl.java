package cn.sabercon.main.ctrl;

import cn.sabercon.common.anno.ServiceController;
import cn.sabercon.main.service.CommentService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

/**
 * @author SaberCon
 * @since 1.0.0
 */
@Api(tags = "评论相关接口")
@ServiceController("comment")
@RequiredArgsConstructor
public class CommentCtrl {

    private final CommentService service;
}
