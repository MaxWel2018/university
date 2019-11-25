package my.university;

import my.university.model.domain.User;
import my.university.model.entity.Role;
import my.university.model.service.ExamResultService;
import my.university.model.service.SpecialityService;
import my.university.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import java.util.HashSet;
import java.util.Set;

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
		Set<Role> roles = new HashSet<>();
		roles.add(new Role(2,"ADMIN"));
//		userService.registration(new User(null, "max@gmail.com", "qwerty1234","Max", "Kru", null,1,null ,null));
		userService.registration(new User(null, "admin@gmail.com", "11111111","Max", "Kruhovykh", null,1,roles ,null));

	}

	//// TODO: 25.11.2019 Если У пользователя 2 роли создать отдельный Профиль файл в котором будут и Информация и возможеости админа

}
