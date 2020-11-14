package cn.sabercon.main.service;

import cn.sabercon.common.json.Json;
import cn.sabercon.common.util.Requests;
import cn.sabercon.main.domain.entity.Comment;
import cn.sabercon.main.domain.model.CommentModel;
import cn.sabercon.main.repo.CommentRepo;
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

    private final UserService userService;

    public Page<CommentModel> list(Long postId) {
        return repo.findByPostId(postId, Requests.ascPageable(Comment.Fields.level)).map(this::convert);
    }

    public CommentModel get(Long id) {
        return repo.findById(id).map(this::convert).orElse(null);
    }

    private CommentModel convert(Comment comment) {
        var model = Json.convert(comment, CommentModel.class);
        model.setUser(userService.getSimpleInfo(comment.getUserId()));
        return model;
    }
}
