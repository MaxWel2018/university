package my.university.model.service;

import my.university.model.domain.ExamResult;
import my.university.model.entity.ExamResultEntity;

import java.util.List;

public interface ExamResultService {

    ExamResultEntity save(ExamResult examResult);

    List<ExamResult> findByUser(Integer id);
}
