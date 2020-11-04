package cn.sabercon.main.ctrl;

import cn.sabercon.common.anno.ServiceController;
import cn.sabercon.common.domian.PageModel;
import cn.sabercon.common.domian.PageQuery;
import cn.sabercon.main.domain.model.CommunityListModel;
import cn.sabercon.main.domain.model.CommunityModel;
import cn.sabercon.main.service.CommunityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author SaberCon
 * @since 1.0.0
 */
@Api(tags = "社区相关接口")
@ServiceController("community")
@RequiredArgsConstructor
public class CommunityCtrl {

    private final CommunityService service;

    @GetMapping("hot")
    @ApiModelProperty("热门分页查询")
    public PageModel<CommunityListModel> listHot(@Valid PageQuery pageQuery) {
        return service.listHot(pageQuery);
    }

    @GetMapping
    public CommunityModel get(@NotNull Long id) {
        return service.get(id);
    }
}
