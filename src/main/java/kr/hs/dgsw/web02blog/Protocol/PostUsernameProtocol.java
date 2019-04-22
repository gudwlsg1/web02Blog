package kr.hs.dgsw.web02blog.Protocol;

import kr.hs.dgsw.web02blog.Domain.Post;

public class PostUsernameProtocol extends Post {
    private String name;

    public PostUsernameProtocol(Post p, String name) {
        super(p);
        this.name = name;
    }
}
