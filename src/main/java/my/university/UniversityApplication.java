package my.university;

import my.university.model.domain.User;
import my.university.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UniversityApplication {
	public static UserService userService;

	@Autowired
	public UniversityApplication(UserService userService) {
		this.userService = userService;
	}

	public static void main(String[] args) {
		SpringApplication.run(UniversityApplication.class, args);

		User build = User.newBuilder()
				.withEmail("max@gmail.com").
						withPassword("qwerty1234")
				.withFirstName("Max")
				.withLastName("Kruhovykh")
				.build();

		userService.registration(build);


	}

}
