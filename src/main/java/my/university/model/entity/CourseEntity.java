package my.university.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity(name = "courseEntity")
@Table(name = "courses")
public class CourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Integer id;
    @Column(name = "course_name", nullable = false)
    private String courseName;
    @OneToOne(mappedBy = "course",cascade = CascadeType.ALL,orphanRemoval = true)
    private ExamResultEntity examResult;
    @ManyToMany(mappedBy = "requiredCourses",fetch = FetchType.EAGER)
    private List<SpecialityEntity> specialities;

    public CourseEntity(String courseName) {
        this.courseName = courseName;
    }
}
