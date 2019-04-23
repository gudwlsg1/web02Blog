package kr.hs.dgsw.web02blog.Controller;

import kr.hs.dgsw.web02blog.Domain.Post;
import kr.hs.dgsw.web02blog.Protocol.ResponseFormat;
import kr.hs.dgsw.web02blog.Protocol.ResponseType;
import kr.hs.dgsw.web02blog.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/post")
    public ResponseFormat list(){
        return new ResponseFormat(
                ResponseType.POST_GET,
                this.postService.lstPost()
        );
    }

    @GetMapping("/post/{id}")
    public ResponseFormat getPost(@PathVariable Long id){
        return new ResponseFormat(
                ResponseType.POST_GET,
                this.postService.getPost(id)
        );
    }

    @PostMapping("/post")
    public ResponseFormat addPost(@RequestBody Post post){
        return new ResponseFormat(
                ResponseType.POST_ADD,
                this.postService.addPost(post)
        );
    }

    @PutMapping("/post/{id}")
    public ResponseFormat editPost(@RequestBody Post post, @PathVariable Long id){
        return new ResponseFormat(
                ResponseType.POST_UPDATE,
                this.postService.editPost(post, id)
        );
    }

    @DeleteMapping("/post/{id}")
    public ResponseFormat deletePost(@PathVariable Long id){
        return new ResponseFormat(
                ResponseType.POST_DELETE,
                this.postService.deletePost(id)
        );
    }

}
