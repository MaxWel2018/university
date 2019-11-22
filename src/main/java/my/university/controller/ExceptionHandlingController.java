package my.university.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Controller
public class ExceptionHandlingController {
    @ExceptionHandler(Exception.class)
    public String excepting() {
        return "error404";
    }
}
