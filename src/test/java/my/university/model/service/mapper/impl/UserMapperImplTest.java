package my.university.model.service.mapper.impl;

import my.university.model.service.mapper.*;

public class UserMapperImplTest {
    private static UserResultMapper userResultMapper = new UserResultMapperImpl();

    private static CourseMapper courseMapper = new CourseMapperImpl();

    private static SpecialityMapper SPECIALITY_MAPPER = new SpecialityMapperImpl(courseMapper, userResultMapper);

    private static final UserMapper USER_MAPPER = new UserMapperImpl(SPECIALITY_MAPPER);
}