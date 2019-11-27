package my.university.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Data
@NoArgsConstructor
@Entity(name = "examResultEntity")
@Table(name = "exam_results")
public class ExamResultEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exam_result_id")
    private Integer id;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "grade")
    private Integer grade;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_course")
    private CourseEntity course;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private UserEntity user;


    private ExamResultEntity(Builder builder) {
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

        public ExamResultEntity build() {
            return new ExamResultEntity(this);
        }
    }
}
