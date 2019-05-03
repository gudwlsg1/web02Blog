package kr.hs.dgsw.web02blog.Protocol;

import kr.hs.dgsw.web02blog.Domain.User;
import lombok.Data;

@Data
public class UserPostCountProtocol extends User {
    private Long Count;

    public UserPostCountProtocol(User u, Long count){
        super(u);
        this.Count = count;
    }
}
