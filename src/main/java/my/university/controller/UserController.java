package my.university.controller;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import my.university.model.domain.*;
import my.university.model.service.ExamResultService;
import my.university.model.service.SpecialityService;
import my.university.model.service.UserResultService;
import my.university.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
@Log4j2
@Controller
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping(value = {"/user"})
public class UserController {

    private static final String DEFAULT_VALUE_NUMBER_SPECIALITY = "1";
    private static final int DEFAULT_SIZE_PAGE = 11;
    private final SpecialityService specialityService;
    private final UserService userService;
    private final ExamResultService examResultService;
    private final UserResultService userResultService;

    @GetMapping(value = {"/profile", "/", ""})
    public String showUserProfile() {
        return "user-profile";
    }

    @GetMapping(value = {"/show-info"})
    public String showInfo(Model model, Principal principal) {
        User user = userService.findByEmail(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("examResult", examResultService.findByUserId(user.getId()));
        model.addAttribute("finalResult", userService.findFinalResultByUserId(user.getId()));
        return "user-info";
    }
    @GetMapping(value = {"/error"})
    public String error() {
        return "/error/error404";
    }

    @GetMapping(value = {"/course-register"})
    public String getCourseRegisterForm(Principal principal,Model model, @RequestParam(value = "speciality", defaultValue = "1") Integer specialityId) {
        model.addAttribute("speciality", specialityService.findById(specialityId));
        Speciality speciality = userService.findByEmail(principal.getName()).getSpeciality();
        if (speciality != null) {
            return "forward:/user/show-info";
        }
        return "course-register";
    }

    @GetMapping(value = {"/courses-submit"})
    public String coursesSubmit(Principal principal, HttpServletRequest request, @RequestParam(name = "speciality", defaultValue = "1") Integer specialityId) {
        Speciality speciality = specialityService.findById(specialityId);
        List<Course> requiredCourses = speciality.getRequiredCourses();
        User user = userService.findByEmail(principal.getName());
        saveExamResult(request, requiredCourses, user);
        User userUpdate = userService.findByEmail(principal.getName());
        userUpdate.setSpeciality(speciality);
        userService.update(userUpdate);
        return "course-reg-ok";
    }

    @GetMapping(value = "/result")
    public String showResult(Model model, @PageableDefault(size = DEFAULT_SIZE_PAGE) Pageable pageable,@RequestParam(defaultValue = DEFAULT_VALUE_NUMBER_SPECIALITY) Integer specialityOption) {
        Page<UserResult> page = userResultService.findBySpecialityId(specialityOption, pageable);
        model.addAttribute("specialities", specialityService.findAll());
        model.addAttribute("specSelectedId", specialityOption);
        model.addAttribute("page", page);
        return "result";
    }

    private ExamResult mapExamResult(HttpServletRequest request, User user, Course requiredCourse) {
        Integer idCourse = requiredCourse.getId();
        return ExamResult.newBuilder().withDate(getDate(request, idCourse)).withCourse(requiredCourse).withUser(user).build();
    }
    private LocalDate getDate(HttpServletRequest request, Integer requiredCourseId) {
        return LocalDate.parse(request.getParameter(String.valueOf(requiredCourseId)));
    }

    private void saveExamResult(HttpServletRequest request, List<Course> requiredCourses, User user) {
        for (Course requiredCourse : requiredCourses) {
            examResultService.save(mapExamResult(request, user, requiredCourse));
        }
    }
}
