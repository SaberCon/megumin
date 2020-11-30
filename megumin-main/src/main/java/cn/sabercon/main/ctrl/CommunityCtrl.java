package cn.sabercon.main.ctrl;

import cn.sabercon.common.anno.CommonController;
import cn.sabercon.main.domain.model.CommunityListModel;
import cn.sabercon.main.domain.model.CommunityModel;
import cn.sabercon.main.service.CommunityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;

import javax.validation.constraints.NotNull;

/**
 * @author SaberCon
 * @since 1.0.0
 */
@Api(tags = "社区相关接口")
@CommonController("community")
@RequiredArgsConstructor
public class CommunityCtrl {

    private final CommunityService service;

    @GetMapping("hot")
    @ApiModelProperty("热门分页查询")
    public Page<CommunityListModel> listHot() {
        return service.listHot();
    }

    @GetMapping
    public CommunityModel get(@NotNull Long id) {
        return service.get(id);
    }
}
