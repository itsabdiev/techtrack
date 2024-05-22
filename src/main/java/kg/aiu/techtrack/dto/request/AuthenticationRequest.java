package kg.aiu.techtrack.dto.request;

public record AuthenticationRequest(
        String email,
        String password
) {
}
