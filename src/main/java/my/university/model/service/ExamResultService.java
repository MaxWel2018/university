package my.university.model.service;

import my.university.model.domain.ExamResult;
import my.university.model.entity.ExamResultEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface ExamResultService {

    ExamResultEntity save(ExamResult examResult);

    List<ExamResult> findByUserId(Integer id);

    ExamResult update(ExamResult examResult);

    List<ExamResult> findAll();


}
