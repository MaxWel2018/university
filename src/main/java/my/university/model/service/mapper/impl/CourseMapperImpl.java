package my.university.model.service.mapper.impl;

import my.university.model.domain.Course;
import my.university.model.entity.CourseEntity;
import my.university.model.service.mapper.CourseMapper;
import org.springframework.stereotype.Component;

@Component
public class CourseMapperImpl implements CourseMapper {

    @Override
    public Course mapEntityToDomain(CourseEntity courseEntity) {
        if (courseEntity == null) {
            return Course.builder().build();
        }
        return Course.builder()
                .id(courseEntity.getId())
                .courseName(courseEntity.getCourseName())
                .build();
    }

    @Override
    public CourseEntity mapDomainToEntity(Course course) {
        if (course == null) {
            return new CourseEntity();
        }
      return   new CourseEntity(course.getCourseName());
    }
}
