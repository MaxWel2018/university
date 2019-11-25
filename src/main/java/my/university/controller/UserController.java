package my.university.controller;

import my.university.model.domain.Course;
import my.university.model.domain.ExamResult;
import my.university.model.domain.User;
import my.university.model.service.ExamResultService;
import my.university.model.service.SpecialityService;
import my.university.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping(value = {"/user"})
public class UserController {

    private final SpecialityService specialityService;
    private final UserService userService;
    private final ExamResultService examResultService;

    @Autowired
    public UserController(SpecialityService specialityService, UserService userService, ExamResultService examResultService) {
        this.specialityService = specialityService;
        this.userService = userService;
        this.examResultService = examResultService;
    }

    @GetMapping(value = {"/profile", "/", ""})
    public String showUserProfile() {
        return "user-profile";
    }

    @GetMapping(value = {"/show-info"})
    public String showInfo(Model model, Principal principal) {
        User user = userService.findByEmail(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("examResult", examResultService.findByUser(user.getId()));
        model.addAttribute("finalResult", userService.findFinalResultByUserId(user.getId()));
        return "user-info";
    }


    @GetMapping(value = {"/course-register"})
    public String getCourseRegisterForm(Model model, @RequestParam(value = "speciality", defaultValue = "1") Integer specialityId) {
        model.addAttribute("speciality", specialityService.findById(specialityId));
        return "course-register";
    }

    @GetMapping(value = {"/courses-submit"})
    public String coursesSubmit(Principal principal, HttpServletRequest request, @RequestParam(name = "speciality", defaultValue = "1") Integer specialityId) {
        List<Course> requiredCourses = specialityService.findById(specialityId).getRequiredCourses();
        User user = userService.findByEmail(principal.getName());
        for (Course requiredCourse : requiredCourses) {
            examResultService.save(mapExamResult(request, user, requiredCourse));
        }
        return "course-reg-ok";
    }

    private ExamResult mapExamResult(HttpServletRequest request, User user, Course requiredCourse) {
        Integer idCourse = requiredCourse.getId();
        return ExamResult.newBuilder().withDate(getDate(request, idCourse)).withCourse(requiredCourse).withUser(user).build();
    }

    private LocalDate getDate(HttpServletRequest request, Integer requiredCourseId) {
        return LocalDate.parse(request.getParameter(String.valueOf(requiredCourseId)));
    }
}
