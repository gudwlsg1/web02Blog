package kr.hs.dgsw.web02blog.Domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue
    private Long id;

    private Long userId;
    private String content;
    private String stroedPath;
    private String originalName;

    @CreationTimestamp
    private LocalDateTime created;
    @UpdateTimestamp
    private LocalDateTime modified;

    public Post(Post p){
        this.id = p.getId();
        this.userId = p.getUserId();
        this.content = p.getContent();
        this.stroedPath = p.getStroedPath();
        this.originalName = p.getOriginalName();
        this.created = p.getCreated();
        this.modified = p.getModified();
    }
}
