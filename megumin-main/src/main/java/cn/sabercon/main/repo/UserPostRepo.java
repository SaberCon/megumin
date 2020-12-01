package cn.sabercon.main.repo;

import cn.sabercon.common.orm.BaseJpaRepository;
import cn.sabercon.main.domain.entity.UserPost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author SaberCon
 * @since 1.0.0
 */
public interface UserPostRepo extends BaseJpaRepository<UserPost> {

    Page<UserPost> findByUserId(Long userId, Pageable pageable);
}
