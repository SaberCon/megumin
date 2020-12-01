package cn.sabercon.main.service;

import cn.sabercon.common.anno.Tx;
import cn.sabercon.common.domian.BaseEntity;
import cn.sabercon.common.json.Json;
import cn.sabercon.common.util.HttpUtils;
import cn.sabercon.main.domain.entity.Community;
import cn.sabercon.main.domain.entity.UserCommunity;
import cn.sabercon.main.domain.model.CommunityListModel;
import cn.sabercon.main.domain.model.CommunityModel;
import cn.sabercon.main.repo.CommunityRepo;
import cn.sabercon.main.repo.UserCommunityRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author SaberCon
 * @since 1.0.0
 */
@Service
@RequiredArgsConstructor
public class CommunityService {

    private final CommunityRepo repo;
    private final UserCommunityRepo userCommunityRepo;

    public Page<CommunityListModel> listHot() {
        return repo.findAll(HttpUtils.descPageable(Community.Fields.members)).map(e -> Json.convert(e, CommunityListModel.class));
    }

    public Page<CommunityListModel> listJoin() {
        return userCommunityRepo.findByUserId(HttpUtils.userId(), HttpUtils.descPageable(BaseEntity.Fields.ctime))
                .map(e -> Json.convert(repo.findByName(e.getCommunityName()).orElseThrow(), CommunityListModel.class));
    }

    public CommunityModel get(String name) {
        return repo.findByName(name).map(e -> Json.convert(e, CommunityModel.class)).orElse(null);
    }

    @Tx
    public void join(String name, Boolean un) {
        UserCommunity example = new UserCommunity();
        example.setUserId(HttpUtils.userId());
        example.setCommunityName(name);
        Optional<UserCommunity> joinOpt = userCommunityRepo.findOne(Example.of(example));
        if (joinOpt.isPresent() && un) {
            userCommunityRepo.delete(joinOpt.get());
        }
        if (joinOpt.isEmpty() && !un) {
            userCommunityRepo.save(example);
        }
    }
}
