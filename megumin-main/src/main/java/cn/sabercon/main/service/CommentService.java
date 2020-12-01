package cn.sabercon.main.service;

import cn.hutool.core.bean.BeanUtil;
import cn.sabercon.common.anno.Tx;
import cn.sabercon.common.json.Json;
import cn.sabercon.common.util.HttpUtils;
import cn.sabercon.main.domain.entity.Comment;
import cn.sabercon.main.domain.model.CommentModel;
import cn.sabercon.main.domain.param.CommentParam;
import cn.sabercon.main.repo.CommentRepo;
import cn.sabercon.main.repo.PostRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

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

    public Page<CommentModel> list(Long postId) {
        return repo.findByPostId(postId, HttpUtils.ascPageable(Comment.Fields.sn)).map(this::convert);
    }

    public CommentModel get(Long id) {
        return repo.findById(id).map(this::convert).orElse(null);
    }

    private CommentModel convert(Comment comment) {
        var model = Json.convert(comment, CommentModel.class);
        model.setCreatedBy(userService.getSimpleInfo(comment.getCreatedBy()));
        return model;
    }

    @Tx
    public void publish(CommentParam param) {
        var comment = new Comment();
        BeanUtil.copyProperties(param, comment);
        comment.setCreatedBy(HttpUtils.userId());
        comment.setReplies(0L);
        var post = postRepo.findById(param.getPostId()).orElseThrow();
        comment.setSn(post.getMaxSn() + 1);
        repo.save(comment);

        post.setMaxSn(post.getMaxSn() + 1);
        post.setComments(post.getComments() + 1);
        post.setLastRepliedBy(comment.getCreatedBy());
    }
}
