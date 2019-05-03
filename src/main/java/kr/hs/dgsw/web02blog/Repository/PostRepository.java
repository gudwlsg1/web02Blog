package kr.hs.dgsw.web02blog.Repository;

import kr.hs.dgsw.web02blog.Domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findTopByUserIdOrderByIdDesc(Long userId);
    //select * from post where userId = userId order by id desc
    Long countAllByUserId(Long userId);
}
