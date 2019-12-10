package my.university.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@Data
@AllArgsConstructor
public class ExamResultsForm {
    private List<ExamResult> examResults;
}
