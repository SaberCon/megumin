package cn.sabercon.main.service;

import cn.sabercon.common.domian.PageModel;
import cn.sabercon.common.domian.PageQuery;
import cn.sabercon.common.json.Json;
import cn.sabercon.main.domain.entity.Comment;
import cn.sabercon.main.domain.model.CommentModel;
import cn.sabercon.main.repo.CommentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author SaberCon
 * @since 1.0.0
 */
@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepo repo;

    private final UserService userService;

    public PageModel<CommentModel> list(Long postId, PageQuery pageQuery) {
        return PageModel.from(repo.findByPostIdOrderByLevel(postId, pageQuery.toPageRequest())).map(this::convert);
    }

    public CommentModel get(Long id) {
        return repo.findById(id).map(this::convert).orElse(null);
    }

    private CommentModel convert(Comment comment) {
        CommentModel model = Json.convert(comment, CommentModel.class);
        model.setUser(userService.getSimpleInfo(comment.getUserId()));
        return model;
    }
}
