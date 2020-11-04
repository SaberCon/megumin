package cn.sabercon.main.service;

import cn.sabercon.common.domian.PageModel;
import cn.sabercon.common.domian.PageQuery;
import cn.sabercon.common.json.Json;
import cn.sabercon.main.domain.entity.Post;
import cn.sabercon.main.domain.model.PostModel;
import cn.sabercon.main.repo.PostRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author SaberCon
 * @since 1.0.0
 */
@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepo repo;

    private final UserService userService;

    public PageModel<PostModel> listRecent(Long communityId, PageQuery pageQuery) {
        return PageModel.from(repo.findByCommunityIdOrderByMtimeDesc(communityId, pageQuery.toPageRequest())).map(this::convert);
    }

    public PostModel get(Long id) {
        return repo.findById(id).map(this::convert).orElse(null);
    }

    private PostModel convert(Post post) {
        PostModel model = Json.convert(post, PostModel.class);
        model.setUser(userService.getSimpleInfo(post.getUserId()));
        return model;
    }
}
