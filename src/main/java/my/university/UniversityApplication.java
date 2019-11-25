package my.university;

import my.university.model.domain.Course;
import my.university.model.domain.ExamResult;
import my.university.model.domain.User;
import my.university.model.service.ExamResultService;
import my.university.model.service.SpecialityService;
import my.university.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import java.time.LocalDate;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class UniversityApplication {
	static UserService userService;

	static SpecialityService specialityService;

	static ExamResultService examResultService;

	@Autowired
	public UniversityApplication(UserService userService, SpecialityService specialityService, ExamResultService examResultService) {
		this.specialityService = specialityService;
		this.userService = userService;
		this.examResultService = examResultService;
	}


	public static void main(String[] args) {
		SpringApplication.run(UniversityApplication.class, args);
//		examResultService.save(ExamResult.newBuilder()
//				.withCourse(new Course(null, "Math", null, null))
//				.withDate(LocalDate.parse("2019-12-11")).withUser(new User(null,"Max@max.com","eqweqwe","max","sss",null,1,null,null)).build());
		userService.registration(new User(null, "max@gmail.com", "qwerty1234","Max", "Kru", null,1,null ,null));

	}

}
