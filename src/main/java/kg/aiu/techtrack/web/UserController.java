package kg.aiu.techtrack.web;


import kg.aiu.techtrack.dto.request.UserUpdateRequest;
import kg.aiu.techtrack.dto.response.MessageResponse;
import kg.aiu.techtrack.endpoint.UserEndpoint;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserController {

    UserEndpoint userEndpoint;

    @PostMapping("/update")
    public MessageResponse update(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody UserUpdateRequest userUpdateRequest) {
        return userEndpoint.update(userUpdateRequest,userDetails.getUsername());
    }

    @PostMapping("/delete")
    public MessageResponse delete(@AuthenticationPrincipal UserDetails userDetails) {
        return userEndpoint.delete(userDetails.getUsername());
    }
}
