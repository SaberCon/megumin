package cn.sabercon.main.repo;

import cn.sabercon.common.orm.BaseJpaRepository;
import cn.sabercon.main.domain.entity.Reply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author SaberCon
 * @since 1.0.0
 */
public interface ReplyRepo extends BaseJpaRepository<Reply> {

    Page<Reply> findByCommentIdOrderByCtimeDesc(Long commentId, Pageable pageable);
}
