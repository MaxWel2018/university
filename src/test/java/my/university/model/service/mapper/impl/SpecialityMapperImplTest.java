package my.university.model.service.mapper.impl;

import my.university.model.domain.Speciality;
import my.university.model.entity.SpecialityEntity;
import my.university.model.service.mapper.CourseMapper;
import my.university.model.service.mapper.SpecialityMapper;
import my.university.model.service.mapper.UserResultMapper;
import my.university.model.service.mapper.UserResultMapperImpl;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SpecialityMapperImplTest {
    private static UserResultMapper userResultMapper = new UserResultMapperImpl();

    private static CourseMapper courseMapper = new CourseMapperImpl();

    private static SpecialityMapper SPECIALITY_MAPPER = new SpecialityMapperImpl(courseMapper, userResultMapper);

    @Test
    public void shouldReturnEmptyObjectWhenParameterNullDomainToEntity() {

        SpecialityEntity specialityEntity = SPECIALITY_MAPPER.mapDomainToEntity(null);
        SpecialityEntity actual = SpecialityEntity.newBuilder().build();

        assertThat(actual, is(equalTo(specialityEntity)));

    }

    @Test
    public void shouldReturnEmptyObjectWhenParameterNullEntityToDomain() {
        Speciality speciality = SPECIALITY_MAPPER.mapEntityToDomain(null);
        Speciality actual = Speciality.newBuilder().build();

        assertThat(actual, is(equalTo(speciality)));
    }

}