package my.university.controller;

import my.university.model.domain.LoginForm;
import my.university.model.domain.Speciality;
import my.university.model.domain.User;
import my.university.model.service.SpecialityService;
import my.university.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = {"/home", "/"})
public class HomeController {
    private static final String DEFAULT_VALUE_NUMBER_SPECIALITY = "1";
    private final SpecialityService specialityService;
    private final UserService userService;

    @Autowired
    public HomeController(SpecialityService specialityService, UserService userService) {
        this.specialityService = specialityService;
        this.userService = userService;
    }


    @RequestMapping(value = {"/", "home"},method = RequestMethod.GET)
    public String getMessage(Model model, @RequestParam(defaultValue = DEFAULT_VALUE_NUMBER_SPECIALITY) Integer specialityOption) {
        List<Speciality> all = specialityService.findAll();
        model.addAttribute("specialities", all);
        model.addAttribute("specSelectedId", specialityOption);
        model.addAttribute("specSelected", specialityService.findById(specialityOption));
        return "home";
    }


    @RequestMapping(value = {"/error"}, method = RequestMethod.GET)
    public String error() {
        return "/error/error404";
    }

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String getLoginForm(@ModelAttribute(name = "loginForm") LoginForm loginForm) {
        return "login";
    }

    @RequestMapping(value = {"/register"}, method = RequestMethod.GET)
    public String getSingUpForm(@ModelAttribute(name = "user") User user) {
        return "sing-up";
    }

    @PostMapping(value = {"/register"})
    public String register(@ModelAttribute(name = "user") @Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "sing-up";
        }

        userService.registration(user);

        user.setPassword("");
        model.addAttribute("loginForm", user);

        return "login";
    }

    @PostMapping(value = {"/login"})
    public String login(Model model, @Valid LoginForm loginForm, BindingResult bindingResult) {
        System.out.println("Working controller /login  Post");
        if (bindingResult.hasErrors()) {
            return "login";
        }
        model.addAttribute("user", loginForm.getEmail());
        return "home";
    }
}
