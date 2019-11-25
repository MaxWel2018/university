package my.university.model.mapper.impl;

import my.university.model.domain.Course;
import my.university.model.entity.CourseEntity;
import my.university.model.mapper.CourseMapper;
import org.springframework.stereotype.Component;

@Component
public class CourseMapperImpl implements CourseMapper {
    @Override
    public Course mapEntityToDomain(CourseEntity courseEntity) {
        return Course.builder().id(courseEntity.getId()).courseName(courseEntity.getCourseName())
                .build();
    }

    @Override
    public CourseEntity mapDomainToEntity(Course course) {
      return   new CourseEntity(course.getCourseName());
    }
}
