package my.university.model.mapper;

import my.university.model.domain.ExamResult;
import my.university.model.entity.ExamResultEntity;

@org.mapstruct.Mapper(componentModel = "spring")
public interface ExamResultMapper extends Mapper<ExamResult, ExamResultEntity> {
}