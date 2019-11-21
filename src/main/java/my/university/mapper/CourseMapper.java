package my.university.mapper;

import my.university.model.domain.Course;
import my.university.model.entity.CourseEntity;

@org.mapstruct.Mapper
public interface CourseMapper extends Mapper<Course, CourseEntity> {
}
