package my.university.controller;

import my.university.model.domain.*;
import my.university.model.service.ExamResultService;
import my.university.model.service.SpecialityService;
import my.university.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

import static java.util.Objects.requireNonNull;

@Controller
@RequestMapping(value = {"/home", "/"})
public class HomeController {
    private static final String DEFAULT_VALUE_NUMBER_SPECIALITY = "1";
    private SpecialityService specialityService;
    private UserService userService;
    private ExamResultService examResultService;

    @Autowired
    public HomeController(SpecialityService specialityService, UserService userService, ExamResultService examResultService) {
        this.specialityService = specialityService;
        this.userService = userService;
        this.examResultService = examResultService;
    }

    @GetMapping(value = {"/", "home"})
    public String getMessage(Model model, @RequestParam(defaultValue = DEFAULT_VALUE_NUMBER_SPECIALITY) Integer specialityOption) {
        model.addAttribute("specialities", specialityService.findAll());
        model.addAttribute("specSelectedId", specialityOption);
        model.addAttribute("specSelected", specialityService.findById(specialityOption));
        return "home";
    }

    @GetMapping(value = {"/error"})
    public String error() {
        return "/error/error404";
    }

    @GetMapping(value = {"/course-register"})
    public String getCourseRegisterForm(Model model, @RequestParam(value = "speciality", defaultValue = "1") Integer specialityId) {
        model.addAttribute("speciality", specialityService.findById(specialityId));
        return "course-register";
    }

    @GetMapping(value = {"/courses-submit"})
    public String coursesSubmit(Principal principal, Model model, HttpServletRequest request, @RequestParam(name = "speciality", defaultValue = "1") Integer specialityId) {
        List<Course> requiredCourses = specialityService.findById(specialityId).getRequiredCourses();
        User user = userService.findByEmail(principal.getName());
        for (Course requiredCourse : requiredCourses) {
            examResultService.save(mapExamResult(request, user, requiredCourse));
        }
        return "course-reg-ok";
    }

    private ExamResult mapExamResult(HttpServletRequest request, User user, Course requiredCourse) {
        Integer idCourse = requiredCourse.getId();
//        requiredCourse.setId(null);
//        user.setId(null);
       return  ExamResult.newBuilder().withDate(getDate(request, idCourse)).withCourse(requiredCourse).withUser(user).build();
    }

    @PostMapping(value = {"/register"})
    public String register(@ModelAttribute(name = "user") @Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "sing-up";
        }

        userService.registration(user);

        user.setPassword("");  // clean password
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

    @GetMapping(value = {"/login"})
    public String getLoginForm(@ModelAttribute(name = "loginForm") LoginForm loginForm) {
        return "login";
    }

    private LocalDate getDate(HttpServletRequest request, Integer requiredCourseId) {
        return LocalDate.parse(request.getParameter(String.valueOf(requiredCourseId)));
    }

}
