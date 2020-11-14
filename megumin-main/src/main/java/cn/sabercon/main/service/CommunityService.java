package cn.sabercon.main.service;

import cn.sabercon.common.json.Json;
import cn.sabercon.common.util.Requests;
import cn.sabercon.main.domain.entity.Community;
import cn.sabercon.main.domain.model.CommunityListModel;
import cn.sabercon.main.domain.model.CommunityModel;
import cn.sabercon.main.repo.CommunityRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
 * @author SaberCon
 * @since 1.0.0
 */
@Service
@RequiredArgsConstructor
public class CommunityService {

    private final CommunityRepo repo;

    public Page<CommunityListModel> listHot() {
        return repo.findAll(Requests.descPageable(Community.Fields.memberCount)).map(e -> Json.convert(e, CommunityListModel.class));
    }

    public CommunityModel get(Long id) {
        return repo.findById(id).map(e -> Json.convert(e, CommunityModel.class)).orElse(null);
    }
}
