package my.university.controller;

import lombok.AllArgsConstructor;
import my.university.model.domain.ExamResult;
import my.university.model.domain.ExamResultsForm;
import my.university.model.domain.User;
import my.university.model.domain.UserResult;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping(value = {"/admin"})
public class AdminController {
    private static final String DEFAULT_VALUE_NUMBER_SPECIALITY = "1";

    private static final int DEFAULT_SIZE_PAGE = 11;

    private static final String DEFAULT_SPECIALITY_ID = "1";

    private final SpecialityService specialityService;

    private final UserService userService;

    private final UserResultService userResultService;

    private final ExamResultService examResultService;

    @GetMapping(value = {"/", "profile", ""})
    public String showAdminProfile() {
        return "admin-profile";
    }

    @GetMapping(value = {"set-grades"})
    public String info(Model model, @RequestParam(name = "specialityOption", defaultValue = DEFAULT_SPECIALITY_ID) Integer specialityID, @PageableDefault(size = DEFAULT_SIZE_PAGE) Pageable pageable) {
        model.addAttribute("allSpeciality", specialityService.findAll());
        model.addAttribute("specSelected", specialityID);
        Page<User> page = userService.findBySpeciality(specialityID, pageable);
        model.addAttribute("page", page);

        return "admin-set-grades-show";
    }

    @GetMapping(value = {"/error"})
    public String error() {
        return "/error/error404";
    }

    @GetMapping(value = "{id}")
    public String getUser(Model model, @PathVariable("id") Integer userId) {
        model.addAttribute("user", userService.findById(userId));
        model.addAttribute("form", new ExamResultsForm(examResultService.findByUserId(userId)));

        return "admin-set-grades-apply";
    }

    @PostMapping(value = "set-grades/apply")
    public String submitGrade(@ModelAttribute ExamResultsForm form) {
        form.getExamResults().forEach(examResultService::update);

        return "grade-apply-ok";
    }

    @GetMapping(value = "update-rating")
    public String submitGrade() {
        List<ExamResult> allExamResult = examResultService.findAll();
        List<UserResult> userResults = userResultService.convertExamResultsToUserResults(allExamResult);
        userResultService.saveAll(userResults);

        return "admin-update-rating";
    }

    @GetMapping(value = "show-enroll-users")
    public String showEnrollUsers(Model model,@RequestParam(defaultValue = DEFAULT_VALUE_NUMBER_SPECIALITY) Integer specialityOption) {
        model.addAttribute("specialities", specialityService.findAll());
        model.addAttribute("specSelectedId", specialityOption);
        model.addAttribute("specSelected", specialityService.findById(specialityOption));

        return "admin-enroll-users";
    }

    @GetMapping(value = "enroll-users")
    public String enrollUsers(@RequestParam(defaultValue = DEFAULT_VALUE_NUMBER_SPECIALITY) Integer specialityOption,Model model) {
        userResultService.enrollUsersByIdSpeciality(specialityOption);
        model.addAttribute("enrollIsOk", true);
        return "forward:/admin/show-enroll-users";
    }
}
