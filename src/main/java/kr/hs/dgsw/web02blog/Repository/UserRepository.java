package kr.hs.dgsw.web02blog.Repository;

import kr.hs.dgsw.web02blog.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByAccountAndUserPassword(String account, String userPassword);
}
