package my.university.controller;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.sql.SQLException;

@ControllerAdvice
public class ExceptionHandlingController {
    @ExceptionHandler(Exception.class)
    public String excepting() {
        return "error/for-other"    ;
    }


    @ExceptionHandler({SQLException.class, DataAccessException.class})
    public String databaseError() {

        return "error/for-other";
    }

}
