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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStroedPath() {
        return stroedPath;
    }

    public void setStroedPath(String stroedPath) {
        this.stroedPath = stroedPath;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }

    public Post(Post p){
        this.id = p.getId();
        this.userId = p.getUserId();
        this.content = p.getContent();
        this.stroedPath = p.getStroedPath();
        this.originalName = p.getOriginalName();
        this.created = p.getCreated();
        this.modified = p.getModified();
    }

    public Post(){

    }
}
