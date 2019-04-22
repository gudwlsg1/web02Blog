package kr.hs.dgsw.web02blog.Controller;

import kr.hs.dgsw.web02blog.Domain.Post;
import kr.hs.dgsw.web02blog.Protocol.PostUsernameProtocol;
import kr.hs.dgsw.web02blog.Service.PostService;
import kr.hs.dgsw.web02blog.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/post")
    public List<PostUsernameProtocol> list(){
        return this.postService.lstPost();
    }

    @GetMapping("/post/{id}")
    public List<PostUsernameProtocol> getPost(@PathVariable Long id){
        return this.postService.getPost(id);
    }

    @PostMapping("/post")
    public Post addPost(@RequestBody Post post){
        return this.postService.addPost(post);
    }

    @PutMapping("/post/{id}")
    public Post editPost(@RequestBody Post post, @PathVariable Long id){
        return this.postService.editPost(post, id);
    }

    @DeleteMapping("/post/{id}")
    public boolean deletePost(@PathVariable Long id){
        return this.postService.deletePost(id);
    }

}
