package kr.hs.dgsw.web02blog.Service;

import kr.hs.dgsw.web02blog.Domain.Post;
import kr.hs.dgsw.web02blog.Protocol.PostUsernameProtocol;

import java.util.List;

public interface PostService {

    List<PostUsernameProtocol> lstPost();
    List<PostUsernameProtocol> getPost(Long id);
    Post addPost(Post post);
    Post editPost(Post post, Long id);
    boolean deletePost(Long id);
}
