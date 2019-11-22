package my.university.controller;

import my.university.model.domain.Speciality;
import my.university.model.domain.User;
import my.university.model.service.SpecialityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.List;

@Controller
@RequestMapping(value = {"/home", "/"})
public class HomeController {
    private static final String DEFAULT_VALUE_NUMBER_SPECIALITY = "1";
    private final SpecialityService specialityService;

    @Autowired
    public HomeController(SpecialityService specialityService) {
        this.specialityService = specialityService;
    }


    @RequestMapping(value = {"/", "home"},method = RequestMethod.GET)
    public String getMessage(Model model, @RequestParam(defaultValue = DEFAULT_VALUE_NUMBER_SPECIALITY) Integer specialityOption) {
        List<Speciality> all = specialityService.findAll();
        model.addAttribute("specialities", all);
        model.addAttribute("specSelectedId", specialityOption);
        model.addAttribute("specSelected", specialityService.findById(specialityOption));
        return "home";
    }

    @RequestMapping(value = {"/login"},method = RequestMethod.GET)
    public String getLoginForm(@ModelAttribute(name = "user") User user,Model model) {
        return "login";
    }

    @RequestMapping(value = {"/login"},method = RequestMethod.POST)
    public String login(@ModelAttribute(name = "user") User user,Model model) {
        @NotEmpty(message = "Please provide a password")
        @Pattern(regexp = "[A-Za-zA-Яа-яёЁ!_#$%^&*()-=+-]{2,32}")
        String userPassword = user.getPassword();

        @Email(message = "*Please provide a valid Email")
        @Pattern(regexp = "[a-zA-Z0-9]{1,}[@]{1}[a-z]{3,}[.]{1}+[a-z]{2,}")
        @NotEmpty(message = "Please provide an email")
        String email = user.getEmail();

        return "login";
    }
}
