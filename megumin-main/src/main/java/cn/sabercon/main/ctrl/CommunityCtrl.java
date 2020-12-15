package cn.sabercon.main.ctrl;

import cn.sabercon.common.anno.CustomController;
import cn.sabercon.main.domain.model.CommunityModel;
import cn.sabercon.main.service.CommunityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotNull;

/**
 * @author SaberCon
 * @since 1.0.0
 */
@CustomController("community")
@RequiredArgsConstructor
public class CommunityCtrl {

    private final CommunityService service;

    @GetMapping("hot")
    public Page<CommunityModel> listHot() {
        return service.listHot();
    }

    @GetMapping("join")
    public Page<CommunityModel> listJoined() {
        return service.listJoined();
    }

    @GetMapping
    public CommunityModel get(@NotNull Long id) {
        return service.get(id);
    }

    @PostMapping("join")
    public void join(@NotNull Long id, @RequestParam(defaultValue = "0") Boolean undo) {
        service.join(id, undo);
    }
}
