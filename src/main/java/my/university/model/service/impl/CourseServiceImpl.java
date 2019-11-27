package my.university.model.service.impl;

import my.university.model.domain.Course;
import my.university.model.mapper.CourseMapper;
import my.university.model.repository.CourseRepository;
import my.university.model.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    private final CourseMapper courseMapper;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository, CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll().stream()
                .map(courseMapper::mapEntityToDomain)
                .collect(Collectors.toList());
    }

}
