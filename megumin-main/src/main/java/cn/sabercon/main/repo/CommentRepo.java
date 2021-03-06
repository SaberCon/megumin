package cn.sabercon.main.repo;

import cn.sabercon.common.jpa.BaseJpaRepository;
import cn.sabercon.main.domain.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author SaberCon
 * @since 1.0.0
 */
public interface CommentRepo extends BaseJpaRepository<Comment> {

    Page<Comment> findByPostIdAndQuoteId(Long postId, Long quoteId, Pageable pageable);

    Page<Comment> findByQuoteId(Long quoteId, Pageable pageable);
}
