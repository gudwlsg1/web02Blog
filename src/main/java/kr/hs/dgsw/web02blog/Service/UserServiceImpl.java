package kr.hs.dgsw.web02blog.Service;

import kr.hs.dgsw.web02blog.Domain.User;
import kr.hs.dgsw.web02blog.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> lstUser() {
        return this.userRepository.findAll();
    }

    @Override
    public User addUser(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public User editUser(User user, Long id) {
        return this.userRepository.findById(id)
                .map(f -> {
                    f.setAccount(Optional.ofNullable(user.getAccount()).orElse(f.getAccount()));
                    f.setEmail(Optional.ofNullable(user.getEmail()).orElse(f.getEmail()));
                    f.setName(Optional.ofNullable(user.getName()).orElse(f.getName()));
                    f.setPhone(Optional.ofNullable(user.getPhone()).orElse(f.getPhone()));
                    f.setProfilePath(Optional.ofNullable(user.getProfilePath()).orElse(f.getProfilePath()));
                    f.setUserPassword(Optional.ofNullable(user.getUserPassword()).orElse(f.getUserPassword()));
                    return this.userRepository.save(f);
                }).orElse(null);
    }

    @Override
    public boolean deleteUser(Long id) {
        try{
            this.userRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public User getUser(Long id) {
        return this.userRepository.findById(id).orElse(null);
    }
}
