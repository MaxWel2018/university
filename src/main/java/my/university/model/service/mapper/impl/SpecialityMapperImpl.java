package my.university.model.service.mapper.impl;

import lombok.AllArgsConstructor;
import my.university.model.domain.Course;
import my.university.model.domain.Speciality;
import my.university.model.entity.CourseEntity;
import my.university.model.entity.SpecialityEntity;
import my.university.model.service.mapper.CourseMapper;
import my.university.model.service.mapper.SpecialityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class SpecialityMapperImpl implements SpecialityMapper {

    private final CourseMapper courseMapper;


    @Override
    public Speciality mapEntityToDomain(SpecialityEntity specialityEntity) {
        if (specialityEntity == null) {
           return Speciality.newBuilder().build();
        }

        return Speciality.newBuilder()
                .withDescription(specialityEntity.getDescription())
                .withExamsStart(specialityEntity.getExamsStart())
                .withExamsEnd(specialityEntity.getExamsEnd())
                .withId(specialityEntity.getId())
                .withName(specialityEntity.getName())
                .withStudentsNumber(specialityEntity.getStudentsNumber())
                .withRequiredCourses(getReqCourses(specialityEntity))
                .build();
    }

    @Override
    public SpecialityEntity mapDomainToEntity(Speciality speciality) {
        if (speciality == null) {
            return SpecialityEntity.newBuilder().build();
        }
        return SpecialityEntity.newBuilder()
                .withDescription(speciality.getDescription())
                .withExamsStart(speciality.getExamsStart())
                .withExamsEnd(speciality.getExamsEnd())
                .withId(speciality.getId())
                .withName(speciality.getName())
                .withStudentsNumber(speciality.getStudentsNumber())
                .withRequiredCourses(getReqCourses(speciality))
                .build();

    }


    private List<Course> getReqCourses(SpecialityEntity specialityEntity) {
        if (specialityEntity == null) {
            return null;
        }
        return specialityEntity.getRequiredCourses()
                .stream()
                .map(courseMapper::mapEntityToDomain)
                .collect(Collectors.toList());

    }

    private List<CourseEntity> getReqCourses(Speciality speciality) {
        if (speciality == null) {
            return null;
        }
        return speciality.getRequiredCourses()
                .stream()
                .map(courseMapper::mapDomainToEntity)
                .collect(Collectors.toList());
    }

}
