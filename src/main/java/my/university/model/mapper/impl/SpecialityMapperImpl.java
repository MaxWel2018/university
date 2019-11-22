package my.university.model.mapper.impl;

import my.university.model.domain.Course;
import my.university.model.domain.Speciality;
import my.university.model.domain.UserResult;
import my.university.model.entity.SpecialityEntity;
import my.university.model.mapper.CourseMapper;
import my.university.model.mapper.SpecialityMapper;
import my.university.model.mapper.UserResultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SpecialityMapperImpl implements SpecialityMapper {
    private final CourseMapper courseMapper;
    private final UserResultMapper userResultMapper;

    @Autowired
    public SpecialityMapperImpl(CourseMapper courseMapper, UserResultMapper userResultMapper) {
        this.courseMapper = courseMapper;
        this.userResultMapper = userResultMapper;
    }

    @Override
    public Speciality mapEntityToDomain(SpecialityEntity specialityEntity) {
        return Speciality.newBuilder()
                .withDescription(specialityEntity.getDescription())
                .withExamsStart(specialityEntity.getExamsStart())
                .withExamsEnd(specialityEntity.getExamsEnd())
                .withId(specialityEntity.getId())
                .withName(specialityEntity.getName())
                .withStudentsNumber(specialityEntity.getStudentsNumber())
                .withRequiredCourses(getReqCourses(specialityEntity))
                .withUserResult(getUserResult(specialityEntity)).build();
    }

    @Override
    public SpecialityEntity mapDomainToEntity(Speciality speciality) {
        throw new  UnsupportedOperationException("In developing");
    }

    private List<UserResult> getUserResult(SpecialityEntity specialityEntity) {
        return specialityEntity.getUserResultEntities().stream().map(userResultMapper::mapEntityToDomain).collect(Collectors.toList());
    }

    private List<Course> getReqCourses(SpecialityEntity specialityEntity) {
        return specialityEntity.getRequiredCourses().stream().map(courseMapper::mapEntityToDomain).collect(Collectors.toList());
    }
}
