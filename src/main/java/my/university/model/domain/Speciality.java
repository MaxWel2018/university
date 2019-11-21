package my.university.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class Speciality {

    private Integer id;
    private String name;
    private Integer studentsNumber;
    private String activity;
    private String background;
    private String employments;
    private LocalDate examsStart;
    private LocalDate examsEnd;
    private List<Course> requiredCourses;
    private List<UserResult> userResult;

    private Speciality(Builder builder) {
        setId(builder.id);
        setName(builder.name);
        setStudentsNumber(builder.studentsNumber);
        setActivity(builder.activity);
        setBackground(builder.background);
        setEmployments(builder.employments);
        setExamsStart(builder.examsStart);
        setExamsEnd(builder.examsEnd);
        setRequiredCourses(builder.requiredCourses);
        setUserResult(builder.userResult);
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {
        private Integer id;
        private String name;
        private Integer studentsNumber;
        private String activity;
        private String background;
        private String employments;
        private LocalDate examsStart;
        private LocalDate examsEnd;
        private List<Course> requiredCourses;
        private List<UserResult> userResult;

        private Builder() {
        }

        public Builder withId(Integer val) {
            id = val;
            return this;
        }

        public Builder withName(String val) {
            name = val;
            return this;
        }

        public Builder withStudentsNumber(Integer val) {
            studentsNumber = val;
            return this;
        }

        public Builder withActivity(String val) {
            activity = val;
            return this;
        }

        public Builder withBackground(String val) {
            background = val;
            return this;
        }

        public Builder withEmployments(String val) {
            employments = val;
            return this;
        }

        public Builder withExamsStart(LocalDate val) {
            examsStart = val;
            return this;
        }

        public Builder withExamsEnd(LocalDate val) {
            examsEnd = val;
            return this;
        }

        public Builder withRequiredCourses(List<Course> val) {
            requiredCourses = val;
            return this;
        }

        public Builder withUserResult(List<UserResult> val) {
            userResult = val;
            return this;
        }

        public Speciality build() {
            return new Speciality(this);
        }
    }
}
