package kr.hs.dgsw.web02blog.Service;

import kr.hs.dgsw.web02blog.Protocol.AttachmentProtocol;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

public interface AttachmentService {
    AttachmentProtocol upload(MultipartFile uploadFile);

    HttpServletResponse download(HttpServletResponse response, String type, long id);
}
