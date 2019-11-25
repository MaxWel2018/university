package my.university.controller;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.sql.SQLException;

@Controller
public class ExceptionHandlingController {
    @ExceptionHandler(Exception.class)
    public String excepting() {
        return "error/for-other"    ;
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(Exception.class)
    public String notFoundError() {
        return "error/error404";
    }

    @ExceptionHandler({SQLException.class, DataAccessException.class})
    public String databaseError() {

        return "error/for-other";
    }
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberFormatException.class)
    public String error() {
        return "error/for-other";
    }
}
