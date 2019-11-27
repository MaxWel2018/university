package my.university.controller;

import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;

@ControllerAdvice
public class ExceptionHandlingController {

    @ExceptionHandler({SQLException.class, DataAccessException.class})
    public String databaseError() {

        return "for-other";
    }

    @ExceptionHandler(Throwable.class)
    public String excepting() {
        return "for-other"    ;
    }

}
