package my.university.model.service.mapper.impl;

import my.university.model.domain.Course;
import my.university.model.entity.CourseEntity;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class CourseMapperImplTest {

    private static final CourseMapperImpl COURSE_MAPPER = new CourseMapperImpl();

    private static final Course COURSE = new Course(1, "Math");

    private static final Course COURSE_WITHOUT_ID = new Course("Math");


    private static final CourseEntity COURSE_ENTITY = new CourseEntity("Math");

    @Test
    public void shouldMapCourseToCourseEntity() {
        CourseEntity courseEntity = COURSE_MAPPER.mapDomainToEntity(COURSE);
        assertThat(COURSE_ENTITY, is(equalTo(courseEntity)));
    }

    @Test
    public void shouldMapCourseEntityToCourse() {
        Course course = COURSE_MAPPER.mapEntityToDomain(COURSE_ENTITY);

        assertThat(COURSE_WITHOUT_ID, is(equalTo(course)));
    }

    @Test
    public void shouldReturnEmptyObjectWhenParameterNullEntityToDomain() {
        Course course = COURSE_MAPPER.mapEntityToDomain(null);
        Course actual = Course.builder().build();
        assertThat(actual, is(equalTo(course)));
    }

    @Test
    public void shouldReturnEmptyObjectWhenParameterNullDomainToEntity() {
        CourseEntity courseEntity = COURSE_MAPPER.mapDomainToEntity(null);
        CourseEntity actual = new CourseEntity();
        assertThat(actual, is(equalTo(courseEntity)));

    }


}