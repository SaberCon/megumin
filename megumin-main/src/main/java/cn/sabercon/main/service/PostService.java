package cn.sabercon.main.service;

import cn.sabercon.common.anno.Tx;
import cn.sabercon.common.domian.BaseEntity;
import cn.sabercon.common.json.Json;
import cn.sabercon.common.util.HttpUtils;
import cn.sabercon.main.domain.entity.Post;
import cn.sabercon.main.domain.entity.UserPost;
import cn.sabercon.main.domain.model.PostModel;
import cn.sabercon.main.repo.PostRepo;
import cn.sabercon.main.repo.UserPostRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author SaberCon
 * @since 1.0.0
 */
@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepo repo;
    private final UserPostRepo userPostRepo;
    private final UserService userService;

    public Page<PostModel> listRecent(String communityName) {
        return repo.findByCommunityName(communityName, HttpUtils.descPageable(BaseEntity.Fields.mtime)).map(this::convert);
    }

    public Page<PostModel> listFollowed() {
        return userPostRepo.findByUserId(HttpUtils.userId(), HttpUtils.descPageable(BaseEntity.Fields.ctime))
                .map(e -> repo.findById(e.getPostId()).orElseThrow()).map(this::convert);
    }

    public PostModel get(Long id) {
        return repo.findById(id).map(this::convert).orElse(null);
    }

    private PostModel convert(Post post) {
        var model = Json.convert(post, PostModel.class);
        model.setCreatedBy(userService.getSimpleInfo(post.getCreatedBy()));
        model.setLastRepliedBy(userService.getSimpleInfo(post.getLastRepliedBy()));
        return model;
    }

    @Tx
    public void follow(Long id, Boolean un) {
        UserPost example = new UserPost();
        example.setUserId(HttpUtils.userId());
        example.setPostId(id);
        Optional<UserPost> followedOpt = userPostRepo.findOne(Example.of(example));
        if (followedOpt.isPresent() && un) {
            userPostRepo.delete(followedOpt.get());
        }
        if (followedOpt.isEmpty() && !un) {
            userPostRepo.save(example);
        }
    }
}
