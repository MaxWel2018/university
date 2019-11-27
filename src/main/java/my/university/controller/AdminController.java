package my.university.controller;

import my.university.model.domain.ExamResult;
import my.university.model.service.CourseService;
import my.university.model.service.ExamResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
@RequestMapping(value = {"/admin"})

public class AdminController {
    public static final int DEFAULT_SIZE_PAGE = 11;
    private static final String DEFAULT_VALUE_NUMBER_COURSE = "1";
    public static final String DEFAULT_DATE = "2019-11-30";
    private final ExamResultService examResultService;

    private final CourseService courseService;



    @Autowired
    public AdminController(ExamResultService examResultService, CourseService courseService) {
        this.examResultService = examResultService;
        this.courseService = courseService;
    }


    @GetMapping(value = {"/", "profile", ""})
    public String showAdminProfile() {
        return "admin-profile";
    }

    @GetMapping(value = {"set-grades"})
    public String info(Model model, @PageableDefault(size = DEFAULT_SIZE_PAGE) Pageable pageable,
                       @RequestParam(defaultValue = DEFAULT_VALUE_NUMBER_COURSE) Integer courseOption,
                       @RequestParam(name = "examDate",defaultValue = DEFAULT_DATE) String date) {
        addAttributes(model,courseOption,pageable ,date);
        return "admin-set-grades";
    }

    private void addAttributes(Model model, @RequestParam(defaultValue = DEFAULT_VALUE_NUMBER_COURSE) Integer courseOption,
                               @PageableDefault(size = DEFAULT_SIZE_PAGE) Pageable pageable,
                               @RequestParam(name = "examDate", defaultValue = DEFAULT_DATE) String date) {
        model.addAttribute("courseSelectedId", courseOption);
        model.addAttribute("allCourse", courseService.findAll());
        model.addAttribute("selectedDate", date);
        Page<ExamResult> page = examResultService.findByCourseIdAndDate(courseOption, LocalDate.parse(date), pageable);
        model.addAttribute("page", page);

    }


}
