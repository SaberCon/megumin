package cn.sabercon.main.service;

import cn.sabercon.common.anno.Tx;
import cn.sabercon.common.util.HttpUtils;
import cn.sabercon.common.util.PojoUtils;
import cn.sabercon.main.domain.entity.Post;
import cn.sabercon.main.domain.entity.UserPost;
import cn.sabercon.main.domain.model.PostModel;
import cn.sabercon.main.domain.param.PostParam;
import cn.sabercon.main.repo.PostRepo;
import cn.sabercon.main.repo.UserPostRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import static cn.sabercon.common.data.QueryUtils.*;

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

    public Page<PostModel> listRecent(Long cid) {
        return repo.findByCommunityId(cid, pagination(DESC_MTIME)).map(this::convert);
    }

    public Page<PostModel> listFollowed() {
        return userPostRepo.findByUserId(HttpUtils.userId(), pagination(DESC_CTIME))
                .map(e -> repo.findById(e.getPostId()).orElseThrow()).map(this::convert);
    }

    public PostModel get(Long id) {
        return repo.findById(id).map(this::convert).orElse(null);
    }

    private PostModel convert(Post post) {
        var model = PojoUtils.convert(post, PostModel.class);
        model.setCreator(userService.getInfo(post.getCreator()));
        return model;
    }

    @Tx
    public void follow(Long id, boolean undo) {
        var userPost = new UserPost();
        userPost.setUserId(HttpUtils.userId());
        userPost.setPostId(id);
        userPostRepo.addRelation(userPost, undo);
    }

    @Tx
    public void publish(PostParam param) {
        var post = PojoUtils.convert(param, Post.class);
        post.setCreator(HttpUtils.userId());
        post.setReplies(0L);
        repo.save(post);
    }
}
