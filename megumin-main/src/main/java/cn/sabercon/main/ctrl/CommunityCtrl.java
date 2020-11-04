package cn.sabercon.main.ctrl;

import cn.sabercon.common.anno.ServiceController;
import cn.sabercon.main.service.CommunityService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

/**
 * @author SaberCon
 * @since 1.0.0
 */
@Api(tags = "社区相关接口")
@ServiceController("community")
@RequiredArgsConstructor
public class CommunityCtrl {

    private final CommunityService service;
}
