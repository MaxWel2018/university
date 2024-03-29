package my.university.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlingController implements ErrorController{

    private final static String ERROR_PATH = "/error";

    @ExceptionHandler(IllegalArgumentException.class)
    public String databaseError() {
        return "IllegalArg";
    }

    @ExceptionHandler(Throwable.class)
    public String excepting() {
        return "for-other";
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

}
