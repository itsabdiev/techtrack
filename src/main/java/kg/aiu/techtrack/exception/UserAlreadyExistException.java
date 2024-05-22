package kg.aiu.techtrack.exception;

public class UserAlreadyExistException extends RuntimeException {

    public UserAlreadyExistException() {
        super("User already exists");
    }
}
