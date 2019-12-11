package my.university.model.service.impl;

import my.university.model.domain.Speciality;
import my.university.model.entity.SpecialityEntity;
import my.university.model.repository.SpecialityRepository;
import my.university.model.service.SpecialityService;
import my.university.model.service.mapper.SpecialityMapper;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SpecialityServiceImplTest {
    @Mock
    private SpecialityRepository specialityRepository;

    @Mock
    private SpecialityMapper specialityMapper;

    @InjectMocks
    private SpecialityServiceImpl specialityService;

    private final Speciality speciality = getSpeciality();

    private final SpecialityEntity specialityEntity = getSpecialityEntity();

    private final List<Speciality> specialities = Arrays.asList(speciality, speciality);

    private final List<SpecialityEntity> specialityEntities  = Arrays.asList(specialityEntity, specialityEntity);

    @After
    public void resetMock() {
        reset(specialityRepository, specialityMapper);
    }

    @Test
    public void shouldReturnListSpecialityWhenFindALL() {
        when(specialityRepository.findAll()).thenReturn(specialityEntities);
        when(specialityMapper.mapEntityToDomain(any(SpecialityEntity.class))).thenReturn(speciality);

        List<Speciality> specialities = specialityService.findAll();
        verify(specialityMapper, times(2)).mapEntityToDomain(any(SpecialityEntity.class));
        assertThat("All speciality found incorrect", specialities, is(equalTo(this.specialities)));

    }

    @Test
    public void shouldReturnSpecialityWhenFindById() {
        when(specialityRepository.findById(anyInt())).thenReturn(Optional.of(specialityEntity));
        when(specialityMapper.mapEntityToDomain(any(SpecialityEntity.class))).thenReturn(speciality);
       Speciality specialities = specialityService.findById(1);

        verify(specialityMapper).mapEntityToDomain(any(SpecialityEntity.class));

        assertThat(" speciality found incorrect", specialities, is(equalTo(this.speciality)));

    }

    private Speciality getSpeciality() {
        return Speciality.newBuilder()
                .withId(1)
                .withStudentsNumber(10)
                .withExamsStart(LocalDate.parse("2019-11-29"))
                .withExamsEnd(LocalDate.parse("2019-12-10"))
                .withName("Compudactor")
                .build();
    }

    private SpecialityEntity getSpecialityEntity() {
        return SpecialityEntity.newBuilder()
                .withId(1)
                .withStudentsNumber(10)
                .withExamsStart(LocalDate.parse("2019-11-29"))
                .withExamsEnd(LocalDate.parse("2019-12-10"))
                .withName("Compudactor")
                .build();
    }


}