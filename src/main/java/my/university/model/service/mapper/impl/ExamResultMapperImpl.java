package my.university.model.service.mapper.impl;

import lombok.AllArgsConstructor;
import my.university.model.domain.Course;
import my.university.model.domain.ExamResult;
import my.university.model.domain.User;
import my.university.model.entity.CourseEntity;
import my.university.model.entity.ExamResultEntity;
import my.university.model.entity.UserEntity;
import my.university.model.service.mapper.CourseMapper;
import my.university.model.service.mapper.ExamResultMapper;
import my.university.model.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ExamResultMapperImpl implements ExamResultMapper {

    private final CourseMapper courseMapper;

    private final UserMapper userMapper;

    @Override
    public ExamResult mapEntityToDomain(ExamResultEntity examResultEntity) {
        if (examResultEntity == null) {
            return ExamResult.newBuilder().build();
        }
        return ExamResult.newBuilder()
                .withId(examResultEntity.getId())
                .withDate(examResultEntity.getDate())
                .withCourse(getCourse(examResultEntity))
                .withUser(getUser(examResultEntity))
                .withGrade(examResultEntity.getGrade())
                .build();
    }

    @Override
    public ExamResultEntity mapDomainToEntity(ExamResult examResult) {
        if (examResult == null) {
            return ExamResultEntity.newBuilder().build();
        }
        return ExamResultEntity.newBuilder()
                .withId(examResult.getId())
                .withCourse(getCourseEntity(examResult))
                .withUser(getUserEntity(examResult))
                .withDate(examResult.getDate())
                .withGrade(examResult.getGrade())
                .build();
    }

    private UserEntity getUserEntity(ExamResult examResult) {
        return Optional.ofNullable(examResult.getUser())
                .map(userMapper::mapDomainToEntity)
                .orElse(null);
    }

    private User getUser(ExamResultEntity examResultEntity) {
        return Optional.ofNullable(examResultEntity.getUser())
                .map(userMapper::mapEntityToDomain)
                .orElse(null);
    }

    private CourseEntity getCourseEntity(ExamResult examResult) {
        return Optional.ofNullable(examResult.getCourse())
                .map(courseMapper::mapDomainToEntity)
                .orElse(null);
    }

    private Course getCourse(ExamResultEntity examResultEntity) {
        return Optional.ofNullable(examResultEntity.getCourse())
                .map(courseMapper::mapEntityToDomain)
                .orElse(null);
    }

}
