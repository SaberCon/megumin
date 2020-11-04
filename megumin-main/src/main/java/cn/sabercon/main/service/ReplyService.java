package cn.sabercon.main.service;

import cn.sabercon.common.domian.PageModel;
import cn.sabercon.common.domian.PageQuery;
import cn.sabercon.common.json.Json;
import cn.sabercon.main.domain.entity.Reply;
import cn.sabercon.main.domain.model.ReplyModel;
import cn.sabercon.main.repo.ReplyRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author SaberCon
 * @since 1.0.0
 */
@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepo repo;

    private final UserService userService;

    public PageModel<ReplyModel> list(Long commentId, PageQuery pageQuery) {
        return PageModel.from(repo.findByCommentIdOrderByCtimeDesc(commentId, pageQuery.toPageRequest())).map(this::convert);
    }

    public ReplyModel get(Long id) {
        return repo.findById(id).map(this::convert).orElse(null);
    }

    private ReplyModel convert(Reply reply) {
        ReplyModel model = Json.convert(reply, ReplyModel.class);
        model.setUser(userService.getSimpleInfo(reply.getUserId()));
        return model;
    }
}
