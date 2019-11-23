package my.university;

import my.university.model.domain.Course;
import my.university.model.domain.ExamResult;
import my.university.model.domain.User;
import my.university.model.entity.Role;
import my.university.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import java.util.ArrayList;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class UniversityApplication {
	static UserService userService;

	public UniversityApplication(UserService userService) {
		this.userService = userService;
	}


	public static void main(String[] args) {
		SpringApplication.run(UniversityApplication.class, args);

//		userService.registration(
//				new User(null, 0, "max@gmail.com", "qwerty1234", "Max", "Kru", null,0 ,null));

	}

}
