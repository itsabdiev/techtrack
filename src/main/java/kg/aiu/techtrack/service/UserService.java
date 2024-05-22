package kg.aiu.techtrack.service;

import kg.aiu.techtrack.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User loadUserByEmail(String email);

    boolean existsByEmail(String email);

    void save(User user);
}
