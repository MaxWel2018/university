package my.university.model.exception;

public class AuthorisationFailException extends RuntimeException {
    public AuthorisationFailException(String message) {
        super(message);
    }
}
