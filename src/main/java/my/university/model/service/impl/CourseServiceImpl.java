package my.university.model.service.impl;

import lombok.AllArgsConstructor;
import my.university.model.domain.Course;
import my.university.model.service.mapper.CourseMapper;
import my.university.model.repository.CourseRepository;
import my.university.model.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    private final CourseMapper courseMapper;

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll().stream()
                .map(courseMapper::mapEntityToDomain)
                .collect(Collectors.toList());
    }

}
