package kr.hs.dgsw.web02blog.Service;

import kr.hs.dgsw.web02blog.Domain.User;
import kr.hs.dgsw.web02blog.Protocol.UserPostCountProtocol;

import java.util.List;

public interface UserService {
    List<User> lstUser();

    User addUser(User user);

    User editUser(User user, Long id);

    boolean deleteUser(Long id);

    User getUser(Long id);

    UserPostCountProtocol login(User user);
}
