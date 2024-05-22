package kg.aiu.techtrack.endpoint;


import io.jsonwebtoken.JwtException;
import kg.aiu.techtrack.dto.request.AuthenticationRequest;
import kg.aiu.techtrack.dto.request.SignUpRequest;
import kg.aiu.techtrack.dto.response.AuthenticationResponse;
import kg.aiu.techtrack.dto.response.MessageResponse;
import kg.aiu.techtrack.entity.User;
import kg.aiu.techtrack.entity.enums.Role;
import kg.aiu.techtrack.exception.UserAlreadyExistException;
import kg.aiu.techtrack.security.util.JsonWebTokenService;
import kg.aiu.techtrack.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;



@Component
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationEndpoint {

    AuthenticationManager authenticationManager;
    UserService userService;
    JsonWebTokenService jsonWebTokenService;
    PasswordEncoder passwordEncoder;

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password())
        );
        User user = userService.loadUserByEmail(request.email());
        String token = jsonWebTokenService.generateToken(user);
        String refreshToken = jsonWebTokenService.generateRefreshToken(user.getEmail());
        return AuthenticationResponse.builder()
                .access_token(token)
                .refresh_token(refreshToken)
                .build();
    }


    public AuthenticationResponse generateNewAccessAndRefreshTokenByRefreshToken(String refreshToken) {
        if (jsonWebTokenService.isTokenExpired(refreshToken)) {
            throw new JwtException("Refresh has been expired");
        }
        String email = jsonWebTokenService.extractUsername(refreshToken);
        User user = userService.loadUserByEmail(email);
        return AuthenticationResponse.builder()
                .access_token(jsonWebTokenService.generateToken(user))
                .refresh_token(jsonWebTokenService.generateRefreshToken(email))
                .build();
    }

    public MessageResponse signUp(SignUpRequest signUpRequest) {

        if (userService.existsByEmail(signUpRequest.email())) {
            throw new UserAlreadyExistException();
        }

        User user = User.builder()
                .role(Role.USER)
                .email(signUpRequest.email())
                .firstName(signUpRequest.firstName())
                .lastName(signUpRequest.lastName())
                .phoneNumber(signUpRequest.phoneNumber())
                .password(passwordEncoder.encode(signUpRequest.password()))
                .build();

        userService.save(user);

        return MessageResponse.builder()
                .message("User has been registered")
                .statusCode(200)
                .build();
    }


}
