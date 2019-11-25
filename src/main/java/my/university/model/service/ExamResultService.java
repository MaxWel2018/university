package my.university.model.service;

import my.university.model.domain.ExamResult;
import my.university.model.entity.ExamResultEntity;

public interface ExamResultService {

    ExamResultEntity save(ExamResult examResult);
}
