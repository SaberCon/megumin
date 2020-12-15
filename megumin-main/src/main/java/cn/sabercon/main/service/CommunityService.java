package cn.sabercon.main.service;

import cn.sabercon.common.anno.Tx;
import cn.sabercon.common.util.HttpUtils;
import cn.sabercon.common.util.PojoUtils;
import cn.sabercon.main.domain.entity.Community;
import cn.sabercon.main.domain.entity.UserCommunity;
import cn.sabercon.main.domain.model.CommunityModel;
import cn.sabercon.main.repo.CommunityRepo;
import cn.sabercon.main.repo.UserCommunityRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import static cn.sabercon.common.data.QueryUtils.DESC_CTIME;
import static cn.sabercon.common.data.QueryUtils.pagination;

/**
 * @author SaberCon
 * @since 1.0.0
 */
@Service
@RequiredArgsConstructor
public class CommunityService {

    private final CommunityRepo repo;
    private final UserCommunityRepo userCommunityRepo;

    public Page<CommunityModel> listHot() {
        return repo.findAll(pagination(repo.getSort(Community::getMembers).descending()))
                .map(e -> PojoUtils.convert(e, CommunityModel.class));
    }

    public Page<CommunityModel> listJoined() {
        return userCommunityRepo.findByUserId(HttpUtils.userId(), pagination(DESC_CTIME))
                .map(e -> PojoUtils.convert(repo.findById(e.getCommunityId()).orElseThrow(), CommunityModel.class));
    }

    public CommunityModel get(Long id) {
        return repo.findById(id).map(e -> PojoUtils.convert(e, CommunityModel.class)).orElse(null);
    }

    @Tx
    public void join(Long id, boolean undo) {
        var userCommunity = new UserCommunity();
        userCommunity.setUserId(HttpUtils.userId());
        userCommunity.setCommunityId(id);
        if (userCommunityRepo.addRelation(userCommunity, undo)) {
            repo.inc(id, Community::getMembers, undo ? -1 : 1);
        }
    }
}
