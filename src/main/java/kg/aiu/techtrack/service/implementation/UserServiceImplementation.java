package kg.aiu.techtrack.service.implementation;

import jakarta.annotation.PostConstruct;
import kg.aiu.techtrack.entity.User;
import kg.aiu.techtrack.entity.enums.Role;
import kg.aiu.techtrack.repository.UserRepository;
import kg.aiu.techtrack.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImplementation implements UserService {

    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return loadUserByEmail(username);
    }

    public User loadUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("User has not been found"));
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @PostConstruct
    void init() {
        if (userRepository.count() < 1) {
            userRepository.save(User.builder()
                    .email("user")
                    .password("$2a$12$eMrvpU2bUFEMKe8vEpLNoOgcefEDFU/VymnC4PwchTMQe3GBEsV1q")
                    .role(Role.USER)
                    .phoneNumber("1234567890")
                    .firstName("User")
                    .lastName("Userov")
                    .enabled(true)
                    .hasBeenRemoved(false)
                    .build()
            );

            userRepository.save(User.builder()
                    .email("manager")
                    .password("$2a$12$ot500PKFu7Ov5kfpLGbVLOU4dQov2eRURL8X6J3dQQbtHjXons4RO")
                    .role(Role.MANAGER)
                    .phoneNumber("1234567890")
                    .firstName("Manager")
                    .lastName("Managerov")
                    .enabled(true)
                    .hasBeenRemoved(false)
                    .build()
            );

            userRepository.save(User.builder()
                    .email("admin")
                    .password("$2a$12$YURgoMXZO8NAfOVBp7rzp.HZaAjEj/xKI9gnuBF2Iif5wv84DXT2e")
                    .role(Role.ADMIN)
                    .phoneNumber("1234567890")
                    .firstName("Admin")
                    .lastName("Adminov")
                    .enabled(true)
                    .hasBeenRemoved(false)
                    .build()
            );
        }
    }
}
