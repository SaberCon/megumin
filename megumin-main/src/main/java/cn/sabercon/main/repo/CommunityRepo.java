package cn.sabercon.main.repo;

import cn.sabercon.common.orm.BaseJpaRepository;
import cn.sabercon.main.domain.entity.Community;

import java.util.Optional;

/**
 * @author SaberCon
 * @since 1.0.0
 */
public interface CommunityRepo extends BaseJpaRepository<Community> {

    Optional<Community> findByName(String name);
}
