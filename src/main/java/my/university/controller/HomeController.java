package my.university.controller;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import my.university.model.domain.LoginForm;
import my.university.model.domain.User;
import my.university.model.exception.EntityAlreadyExistException;
import my.university.model.service.SpecialityService;
import my.university.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@Log4j2
@Controller
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping(value = {"/home", "/"})
public class HomeController {

    private static final String DEFAULT_VALUE_NUMBER_SPECIALITY = "1";
    private SpecialityService specialityService;
    private UserService userService;

    @GetMapping(value = {"/", "home"})
    public String showHomePage(Model model, @RequestParam(defaultValue = DEFAULT_VALUE_NUMBER_SPECIALITY) Integer specialityOption) {
        model.addAttribute("specialities", specialityService.findAll());
        model.addAttribute("specSelectedId", specialityOption);
        model.addAttribute("specSelected", specialityService.findById(specialityOption));
        return "home";
    }

    @PostMapping(value = {"/register"})
    public String register(@ModelAttribute(name = "user") @Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "sing-up";
        }

        try {
            userService.registration(user);
            log.info("User {} registered",user);
        } catch (EntityAlreadyExistException e) {
            model.addAttribute("entityAlreadyExist", true);
            return "sing-up";

        }

        user.setPassword(null);
        model.addAttribute("loginForm", user);
        return "login";
    }

    @GetMapping(value = {"/register"})
    public String getSingUpForm(@ModelAttribute(name = "user") User user) {
        return "sing-up";
    }

    @PostMapping(value = {"/login"})
    public String login(Model model, @Valid LoginForm loginForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "login";
        }
        model.addAttribute("user", loginForm.getEmail());
        return "home";
    }

    @GetMapping(value = {"/error"})
    public String error() {
        return "/error/error404";
    }

    @GetMapping(value = {"/login"})
    public String getLoginForm(@ModelAttribute(name = "loginForm") LoginForm loginForm) {
        return "login";
    }



}
