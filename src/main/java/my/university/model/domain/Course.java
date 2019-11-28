package my.university.model.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course {

    private  Integer id;

    @NotNull
    private  String courseName;

    public Course(@NotNull String courseName) {
        this.courseName = courseName;
    }
}
