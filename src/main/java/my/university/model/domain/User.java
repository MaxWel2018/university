package my.university.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import my.university.model.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class User {

    private Integer id;
    @Value("${spring.queries.roles-query}")
    private String string;
    @Pattern(regexp = "[a-zA-Z0-9]{1,}[@]{1}[a-z]{3,}[.]{1}+[a-z]{2,}" , message = "enter the email in the specified format : name@domain.com")
    private String email;
    @Pattern(regexp = "[A-Za-zA-Яа-яёЁ!_#$%^&*()-=+-]{2,32}", message = "Password inCorrect")
    private String password;
    @Pattern(regexp = "[A-Za-zА-Яа-яёЁ]{2,200}", message = "Last name inCorrect")
    private String firstName;
    @Pattern(regexp = "[A-Za-zА-Яа-яёЁ]{2,200}", message = "Last name inCorrect")
    private String lastName;
    private Speciality speciality;
    private Integer active;
    private Set<Role> roles;
    List<ExamResult> examResults;

    private User(Builder builder) {
        setExamResults(builder.examResults);
        setId(builder.id);
        setEmail(builder.email);
        setPassword(builder.password);
        setFirstName(builder.firstName);
        setLastName(builder.lastName);
        setSpeciality(builder.speciality);
        setActive(builder.active);
        setRoles(builder.roles);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {

        private List<ExamResult> examResults;
        private Integer id;
        private @Email(message = "*Please provide a valid Email") @Pattern(regexp = "[a-zA-Z0-9]{1,}[@]{1}[a-z]{3,}[.]{1}+[a-z]{2,}") @NotEmpty(message = "Please provide an email") String email;
        private @NotEmpty(message = "Please provide a password") @Pattern(regexp = "[A-Za-zA-Яа-яёЁ!_#$%^&*()-=+-]{2,32}") String password;
        private @NotEmpty(message = "Please provide your name") @Pattern(regexp = "[A-Za-zА-Яа-яёЁ]{2,200}") String firstName;
        private @Pattern(regexp = "[A-Za-zА-Яа-яёЁ]{2,200}") @NotEmpty(message = "Please provide your surname") String lastName;
        private Speciality speciality;
        private Integer active;
        private Set<Role> roles;

        private Builder() {
        }

        public Builder withExamResults(List<ExamResult> val) {
            examResults = val;
            return this;
        }

        public Builder withId(Integer val) {
            id = val;
            return this;
        }

        public Builder withEmail(@Email(message = "*Please provide a valid Email") @Pattern(regexp = "[a-zA-Z0-9]{1,}[@]{1}[a-z]{3,}[.]{1}+[a-z]{2,}") @NotEmpty(message = "Please provide an email") String val) {
            email = val;
            return this;
        }

        public Builder withPassword(@NotEmpty(message = "Please provide a password") @Pattern(regexp = "[A-Za-zA-Яа-яёЁ!_#$%^&*()-=+-]{2,32}") String val) {
            password = val;
            return this;
        }

        public Builder withFirstName(@NotEmpty(message = "Please provide your name") @Pattern(regexp = "[A-Za-zА-Яа-яёЁ]{2,200}") String val) {
            firstName = val;
            return this;
        }

        public Builder withLastName(@Pattern(regexp = "[A-Za-zА-Яа-яёЁ]{2,200}") @NotEmpty(message = "Please provide your surname") String val) {
            lastName = val;
            return this;
        }

        public Builder withSpeciality(Speciality val) {
            speciality = val;
            return this;
        }

        public Builder withActive(Integer val) {
            active = val;
            return this;
        }

        public Builder withRoles(Set<Role> val) {
            roles = val;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}