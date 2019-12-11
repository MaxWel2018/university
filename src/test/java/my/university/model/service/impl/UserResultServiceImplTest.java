package my.university.model.service.impl;

import my.university.model.domain.User;
import my.university.model.domain.UserResult;
import my.university.model.entity.UserEntity;
import my.university.model.entity.UserResultEntity;
import my.university.model.repository.UserResultRepository;
import my.university.model.service.SpecialityService;
import my.university.model.service.mapper.UserResultMapper;
import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserResultServiceImplTest {

    @Mock
    private User user;

    @Mock
    private UserEntity userEntity;

    @Mock
    private UserResultRepository userResultRepository;

    @Mock
    private SpecialityService specialityService;

    @Mock
    private UserResultMapper userResultMapper;

    @InjectMocks
    private UserResultServiceImpl userResultService;

    private final UserResult userResult = getUserResult();

    private final UserResultEntity userResultEntity = getUserResultEntity();

    @After
    public void resetMock() {
        reset(specialityService, userResultRepository, userResultMapper, user, userEntity);
    }

    @Ignore
    @Test
    public void shouldTestSaveMethod() {
        when(userResultRepository.findByUserId(any())).thenReturn(Optional.of(userResultEntity));
        when(userResultMapper.mapDomainToEntity(any(UserResult.class))).thenReturn(userResultEntity);

        when(user.getId()).thenReturn(anyInt());

        userResultService.save(userResult);

        verify(userResultRepository).findByUserId(anyInt());
        verify(userResultMapper).mapDomainToEntity(any(UserResult.class));

    }

    private UserResultEntity getUserResultEntity() {
        return UserResultEntity.newBuilder()
                .withId(1)
                .withUserEntity(userEntity)
                .withFinalMark(100)
                .withConfirmed(true)
                .build();
    }

    private UserResult getUserResult() {
        return UserResult.newBuilder()
                .withId(1)
                .withUser(user)
                .withFinalMark(100)
                .withConfirmed(true)
                .build();
    }

}