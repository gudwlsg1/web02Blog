package kr.hs.dgsw.web02blog.Repository;

import kr.hs.dgsw.web02blog.Domain.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
    Optional<Attachment> findByPostId(Long postId);
}
