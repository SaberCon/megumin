package cn.sabercon.main.repo;

import cn.sabercon.common.orm.BaseJpaRepository;
import cn.sabercon.common.orm.CustomRepository;
import cn.sabercon.main.domain.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author SaberCon
 * @since 1.0.0
 */
public interface CommentRepo extends BaseJpaRepository<Comment>, CustomRepository<Comment> {

    Page<Comment> findByPostId(Long postId, Pageable pageable);
}
