package my.university.model.service.impl;

import my.university.model.domain.Course;
import my.university.model.entity.CourseEntity;
import my.university.model.service.mapper.CourseMapper;
import my.university.model.repository.CourseRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CourseServiceImplTest {
    @Mock
    private  CourseRepository courseRepository;
    @Mock
    private CourseMapper courseMapper;
    @InjectMocks
    private CourseServiceImpl courseService;


    private CourseEntity courseEntity = new CourseEntity("Math");
    private Course course = new Course(1,"Math");

    @After
    public void resetMock() {
        reset(courseRepository, courseMapper);
    }

    @Test
    public void shouldReturnCoursesWhenFindAll() {
        List<CourseEntity> coursesEntity = Collections.singletonList(courseEntity);
        List<Course> courses = Collections.singletonList(course);
        when(courseRepository.findAll()).thenReturn(coursesEntity);
        when(courseMapper.mapEntityToDomain(courseEntity)).thenReturn(course);

        List<Course> coursesAll = courseService.findAll();

        verify(courseRepository).findAll();
        verify(courseMapper).mapEntityToDomain(any(CourseEntity.class));

        assertThat(coursesAll, is(equalTo(courses)));

    }

}