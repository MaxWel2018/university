package my.university.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@Entity(name = "specialityEntity")
@Table(name = "specialities")
public class SpecialityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "speciality_id")
    private Integer id;

    @Column(name = "speciality_name")
    private String name;

    @Column(name = "students_number")
    private Integer studentsNumber;

    @Column(name = "description",length = 3000)
    private String description;

    @Column(name = "exams_start")
    private LocalDate examsStart;

    @Column(name = "exam_end")
    private LocalDate examsEnd;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "speciality_courses",
            joinColumns = @JoinColumn(name = "id_speciality"),
            inverseJoinColumns = @JoinColumn(name = "id_course"))
    private List<CourseEntity> requiredCourses;

    @OneToMany(mappedBy = "specialityEntity", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, orphanRemoval = true)
    private List<UserResultEntity> userResultEntities;

    private SpecialityEntity(Builder builder) {
        setId(builder.id);
        setName(builder.name);
        setStudentsNumber(builder.studentsNumber);
        setDescription(builder.description);
        setExamsStart(builder.examsStart);
        setExamsEnd(builder.examsEnd);
        setRequiredCourses(builder.requiredCourses);
        setUserResultEntities(builder.userResultEntities);
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {
        private Integer id;
        private String name;
        private Integer studentsNumber;
        private String description;
        private LocalDate examsStart;
        private LocalDate examsEnd;
        private List<CourseEntity> requiredCourses;
        private List<UserResultEntity> userResultEntities;

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

        public Builder withDescription(String val) {
            description = val;
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

        public Builder withRequiredCourses(List<CourseEntity> val) {
            requiredCourses = val;
            return this;
        }

        public Builder withUserResultEntities(List<UserResultEntity> val) {
            userResultEntities = val;
            return this;
        }

        public SpecialityEntity build() {
            return new SpecialityEntity(this);
        }
    }

    @Override
    public String toString() {
        return "SpecialityEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", studentsNumber=" + studentsNumber +
                ", description='" + description + '\'' +
                ", examsStart=" + examsStart +
                ", examsEnd=" + examsEnd +
                ", requiredCourses=" + requiredCourses +
                ", userResultEntities=" + userResultEntities +
                '}';
    }
    //TODO Защита от зацикливания MapStruct
}
