package my.university.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import my.university.model.entity.CourseEntity;
import my.university.model.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ExamResult {

    private Integer id;

    private LocalDate date;

    private Integer grade;

    private CourseEntity course;

    private UserEntity user;

    private ExamResult(Builder builder) {
        setId(builder.id);
        setDate(builder.date);
        setGrade(builder.grade);
        setCourse(builder.course);
        setUser(builder.user);
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {
        private Integer id;
        private LocalDate date;
        private Integer grade;
        private CourseEntity course;
        private UserEntity user;

        private Builder() {
        }

        public Builder withId(Integer val) {
            id = val;
            return this;
        }

        public Builder withDate(LocalDate val) {
            date = val;
            return this;
        }

        public Builder withGrade(Integer val) {
            grade = val;
            return this;
        }

        public Builder withCourse(CourseEntity val) {
            course = val;
            return this;
        }

        public Builder withUser(UserEntity val) {
            user = val;
            return this;
        }

        public ExamResult build() {
            return new ExamResult(this);
        }
    }
}
