package my.university.model.exception;

public class EntityAlreadyExistException extends RuntimeException {

    public EntityAlreadyExistException(String msg) {
        super(msg);
    }

}
