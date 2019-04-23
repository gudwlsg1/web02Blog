package kr.hs.dgsw.web02blog.Service;

import kr.hs.dgsw.web02blog.Domain.Post;
import kr.hs.dgsw.web02blog.Domain.User;
import kr.hs.dgsw.web02blog.Protocol.PostUsernameProtocol;
import kr.hs.dgsw.web02blog.Repository.PostRepository;
import kr.hs.dgsw.web02blog.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;


    @Override
    public List<PostUsernameProtocol> lstPost() {
        List<Post> lstPost = this.postRepository.findAll();
        List<PostUsernameProtocol> lstPostUsernameProtocol = new ArrayList<>();

        lstPost.forEach(p -> {
            Optional<User> found = this.userRepository.findById(p.getUserId());
            String name = found.isPresent() ? found.get().getName() : null;
            lstPostUsernameProtocol.add(new PostUsernameProtocol(p, name));
        });
        return lstPostUsernameProtocol;
    }

    @Override
    public List<PostUsernameProtocol> getPost(Long id) {
        Optional<User> found = this.userRepository.findById(id);
        String name = found.isPresent() ? found.get().getName() : null;

        List<Post> lstPost = this.postRepository.findAll();
        List<PostUsernameProtocol> lstPostUsernameProtocol = new ArrayList<>();

        lstPost.forEach(p -> {
            if(p.getId() == id){
                lstPostUsernameProtocol.add(new PostUsernameProtocol(p, name));
            }
        });

        return lstPostUsernameProtocol;
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
                    f.setPictures(post.getPictures().size() > 0 ? post.getPictures() : f.getPictures());
                    return this.postRepository.save(f);
                }).orElse(null);
    }

    @Override
    public boolean deletePost(Long id) {
        try{
            this.postRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
