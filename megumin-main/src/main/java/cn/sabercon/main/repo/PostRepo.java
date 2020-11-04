package cn.sabercon.main.repo;

import cn.sabercon.common.data.BaseJpaRepository;
import cn.sabercon.main.domain.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author SaberCon
 * @since 1.0.0
 */
public interface PostRepo extends BaseJpaRepository<Post> {

    Page<Post> findByCommunityIdOrderByMtimeDesc(Long communityId, Pageable pageable);
}
