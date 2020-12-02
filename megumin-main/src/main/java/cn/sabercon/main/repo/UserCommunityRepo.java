package cn.sabercon.main.repo;

import cn.sabercon.common.jpa.BaseJpaRepository;
import cn.sabercon.main.domain.entity.UserCommunity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author SaberCon
 * @since 1.0.0
 */
public interface UserCommunityRepo extends BaseJpaRepository<UserCommunity> {

    Page<UserCommunity> findByUserId(Long userId, Pageable pageable);
}
