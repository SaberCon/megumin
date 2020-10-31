package cn.sabercon.main.repo;

import cn.sabercon.common.data.BaseJpaRepository;
import cn.sabercon.main.domain.entity.User;

import java.util.Optional;

/**
 * @author SaberCon
 * @since 1.0.0
 */
public interface UserRepo extends BaseJpaRepository<User> {

    Optional<User> findByPhone(String phone);

    boolean existsByUsername(String username);
}
