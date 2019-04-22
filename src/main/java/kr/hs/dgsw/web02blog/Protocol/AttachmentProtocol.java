package kr.hs.dgsw.web02blog.Protocol;

import lombok.Data;

@Data
public class AttachmentProtocol {
    private String stroedPath;
    private String originalName;

    public AttachmentProtocol(String stroedPath, String originalName){
        this.stroedPath = stroedPath;
        this.originalName = originalName;
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
}
