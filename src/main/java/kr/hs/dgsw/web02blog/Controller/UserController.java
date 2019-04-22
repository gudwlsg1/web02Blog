package kr.hs.dgsw.web02blog.Controller;

import kr.hs.dgsw.web02blog.Domain.User;
import kr.hs.dgsw.web02blog.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public List<User> list(){
        return this.userService.lstUser();
    }

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable Long id){
        return this.userService.getUser(id);
    }

    @PostMapping("/user")
    public User addUser(@RequestBody User user){
        return this.userService.addUser(user);
    }

    @PutMapping("/user/{id}")
    public User editUser(@RequestBody User user, @PathVariable Long id){
        return this.userService.editUser(user, id);
    }

    @DeleteMapping("/user/{id}")
    public boolean deleteUser(@PathVariable Long id){
        return this.userService.deleteUser(id);
    }
}
