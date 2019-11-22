package my.university.model.mapper;

import my.university.model.domain.Course;
import my.university.model.entity.CourseEntity;

public interface CourseMapper {

    Course mapEntityToDomain(CourseEntity courseEntity);

    CourseEntity mapDomainToEntity(Course course);
}
