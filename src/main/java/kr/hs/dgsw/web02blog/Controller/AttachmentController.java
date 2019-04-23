package kr.hs.dgsw.web02blog.Controller;

import kr.hs.dgsw.web02blog.Protocol.AttachmentProtocol;
import kr.hs.dgsw.web02blog.Protocol.ResponseFormat;
import kr.hs.dgsw.web02blog.Protocol.ResponseType;
import kr.hs.dgsw.web02blog.Service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class AttachmentController {

    @Autowired
    AttachmentService attachmentService;

    @PostMapping("/attachment")
    public ResponseFormat upload(@RequestPart MultipartFile uploadFile){
        return new ResponseFormat(
                ResponseType.ATTACHMENT_STORED,
                this.attachmentService.upload(uploadFile)
        );
    }

    @GetMapping("/attachment/{type}/{id}")
    public void download(@PathVariable String type, @PathVariable long id, HttpServletRequest request, HttpServletResponse response){
        response = this.attachmentService.download(response, type, id);
    }
}
