package kg.aiu.techtrack.endpoint;


import kg.aiu.techtrack.dto.request.UserUpdateRequest;
import kg.aiu.techtrack.dto.response.MessageResponse;
import kg.aiu.techtrack.entity.User;
import kg.aiu.techtrack.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserEndpoint {

    UserService userService;

    public MessageResponse update(UserUpdateRequest userUpdateRequest,String email) {
        User loadUserByEmail = userService.loadUserByEmail(email);
        Long id = loadUserByEmail.getId();

        User userToBeUpdated = User.builder()
                .id(id)
                .firstName(userUpdateRequest.firstName())
                .lastName(userUpdateRequest.lastName())
                .phoneNumber(userUpdateRequest.phoneNumber())
                .build();

        userService.save(userToBeUpdated);

        return MessageResponse.builder()
                .message("User has been updated")
                .statusCode(200)
                .build();
    }

    public MessageResponse delete(String username) {

        User user = userService.loadUserByEmail(username);

        userService.delete(user);

        return MessageResponse.builder()
                .message("User has been deleted")
                .statusCode(200)
                .build();
    }
}
