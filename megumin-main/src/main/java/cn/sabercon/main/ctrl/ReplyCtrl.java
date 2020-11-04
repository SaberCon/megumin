package cn.sabercon.main.ctrl;

import cn.sabercon.common.anno.ServiceController;
import cn.sabercon.main.service.ReplyService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

/**
 * @author SaberCon
 * @since 1.0.0
 */
@Api(tags = "回复相关接口")
@ServiceController("reply")
@RequiredArgsConstructor
public class ReplyCtrl {

    private final ReplyService service;
}
