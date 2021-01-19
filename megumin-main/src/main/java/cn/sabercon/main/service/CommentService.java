package cn.sabercon.main.service;

import cn.sabercon.common.anno.Tx;
import cn.sabercon.common.util.HttpUtils;
import cn.sabercon.common.util.PojoUtils;
import cn.sabercon.main.domain.entity.Comment;
import cn.sabercon.main.domain.entity.Post;
import cn.sabercon.main.domain.model.CommentModel;
import cn.sabercon.main.domain.param.CommentParam;
import cn.sabercon.main.repo.CommentRepo;
import cn.sabercon.main.repo.PostRepo;
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
public class CommentService {

    private final CommentRepo repo;

    private final PostRepo postRepo;

    private final UserService userService;

    public Page<CommentModel> listByPostId(Long postId) {
        return repo.findByPostIdAndQuoteId(postId, Comment.TOP_COMMENT, pagination(DESC_CTIME))
                .map(this::convert);
    }

    public Page<CommentModel> listByQuoteId(Long quoteId) {
        return repo.findByQuoteId(quoteId, pagination(DESC_CTIME)).map(this::convert);
    }

    public CommentModel get(Long id) {
        return repo.findById(id).map(this::convert).orElse(null);
    }

    private CommentModel convert(Comment comment) {
        var model = PojoUtils.convert(comment, CommentModel.class);
        model.setCreator(userService.getInfo(comment.getCreator()));
        return model;
    }

    @Tx
    public void publish(CommentParam param) {
        var comment = PojoUtils.convert(param, Comment.class);
        comment.setCreator(HttpUtils.userId());
        repo.save(comment);
        repo.inc(param.getQuoteId(), Comment::getReplies, 1);
        postRepo.inc(param.getPostId(), Post::getReplies, 1);
    }
}
