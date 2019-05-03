package kr.hs.dgsw.web02blog.Service;

import kr.hs.dgsw.web02blog.Domain.Post;
import kr.hs.dgsw.web02blog.Domain.User;
import kr.hs.dgsw.web02blog.Protocol.PostUsernameProtocol;
import kr.hs.dgsw.web02blog.Repository.PostRepository;
import kr.hs.dgsw.web02blog.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;


    @Override
    public List<PostUsernameProtocol> lstPost() {
        List<Post> lstPost = this.postRepository.findAll();


        /*//오름차순
        Collections.sort(lstPost, new Comparator<Post>(){
            @Override
            public int compare(Post p1, Post p2) {
                return p1.getCreated().compareTo(p2.getCreated());
            }
        });
        //반대로
        Collections.reverse(lstPost);*/

        List<PostUsernameProtocol> lstPostUsernameProtocol = new ArrayList<>();

        lstPost.forEach(p -> {
            Optional<User> found = this.userRepository.findById(p.getUserId());
            String name = found.isPresent() ? found.get().getName() : null;
            lstPostUsernameProtocol.add(new PostUsernameProtocol(p, name));
        });
        return lstPostUsernameProtocol;
    }

    @Override
    public PostUsernameProtocol getPost(Long id) {
        Post post = this.postRepository.findById(id).get();

        if(post == null){
            return null;
        }

        Optional<User> found = this.userRepository.findById(post.getUserId());
        String name = found.isPresent() ? found.get().getName() : null;

        PostUsernameProtocol postUsernameProtocol = new PostUsernameProtocol(post, name);

        return postUsernameProtocol;
    }

    @Override
    public Post addPost(Post post) {
        return this.postRepository.save(post);
    }

    @Override
    public Post editPost(Post post, Long id) {
        return this.postRepository.findById(id)
                .map(f -> {
                    f.setTitle(Optional.ofNullable(post.getTitle()).orElse(f.getContent()));
                    f.setContent(Optional.ofNullable(post.getContent()).orElse(f.getContent()));
                    //f.setPictures(post.getPictures().size() > 0 ? post.getPictures() : f.getPictures());
                    return this.postRepository.save(f);
                }).orElse(null);
    }

    @Override
    public boolean deletePost(Long id) {
        try {
            this.postRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
