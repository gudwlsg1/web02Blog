package kr.hs.dgsw.web02blog.Controller;

import kr.hs.dgsw.web02blog.Domain.User;
import kr.hs.dgsw.web02blog.Protocol.ResponseFormat;
import kr.hs.dgsw.web02blog.Protocol.ResponseType;
import kr.hs.dgsw.web02blog.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public ResponseFormat list(){
        return new ResponseFormat(
                ResponseType.USER_GET,
                this.userService.lstUser()
        );
    }

    @GetMapping("/user/{id}")
    public ResponseFormat getUser(@PathVariable Long id){
        return new ResponseFormat(
                ResponseType.USER_GET,
                this.userService.getUser(id)
        );
    }

    @PostMapping("/user")
    public ResponseFormat addUser(@RequestBody User user){
        return new ResponseFormat(
                ResponseType.USER_ADD,
                this.userService.addUser(user)
        );
    }

    @PutMapping("/user/{id}")
    public ResponseFormat editUser(@RequestBody User user, @PathVariable Long id){
        return new ResponseFormat(
                ResponseType.USER_UPDATE,
                this.userService.editUser(user, id)
        );
    }

    @DeleteMapping("/user/{id}")
    public ResponseFormat deleteUser(@PathVariable Long id){
        return new ResponseFormat(
                ResponseType.USER_DELETE,
                this.userService.deleteUser(id)
        );
    }
}
