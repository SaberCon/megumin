package cn.sabercon.main.service;

import cn.sabercon.common.anno.Tx;
import cn.sabercon.common.domian.BaseEntity;
import cn.sabercon.common.util.HttpUtils;
import cn.sabercon.common.util.PojoUtils;
import cn.sabercon.main.domain.entity.Comment;
import cn.sabercon.main.domain.entity.Post;
import cn.sabercon.main.domain.entity.Reply;
import cn.sabercon.main.domain.model.ReplyModel;
import cn.sabercon.main.domain.param.ReplyParam;
import cn.sabercon.main.repo.CommentRepo;
import cn.sabercon.main.repo.PostRepo;
import cn.sabercon.main.repo.ReplyRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author SaberCon
 * @since 1.0.0
 */
@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepo repo;
    private final CommentRepo commentRepo;
    private final PostRepo postRepo;
    private final UserService userService;

    public Page<ReplyModel> list(Long commentId) {
        return repo.findByCommentId(commentId, HttpUtils.descPageable(BaseEntity.Fields.ctime)).map(this::convert);
    }

    public ReplyModel get(Long id) {
        return repo.findById(id).map(this::convert).orElse(null);
    }

    private ReplyModel convert(Reply reply) {
        var model = PojoUtils.convert(reply, ReplyModel.class);
        model.setCreatedBy(userService.getSimpleInfo(reply.getCreatedBy()));
        if (Objects.nonNull(reply.getRepliedUser())) {
            model.setRepliedUser(userService.getSimpleInfo(reply.getRepliedUser()));
        }
        return model;
    }

    @Tx
    public void publish(ReplyParam param) {
        var reply = PojoUtils.convert(param, Reply.class);
        reply.setCreatedBy(HttpUtils.userId());
        var comment = commentRepo.findById(param.getCommentId()).orElseThrow();
        reply.setPostId(comment.getPostId());
        repo.save(reply);

        commentRepo.increment(comment.getId(), Comment.Fields.replies);
        postRepo.increment(comment.getPostId(), Post.Fields.comments);
    }
}
