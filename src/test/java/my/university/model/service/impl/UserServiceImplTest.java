package my.university.model.service.impl;

import my.university.model.domain.User;
import my.university.model.entity.Role;
import my.university.model.entity.UserEntity;
import my.university.model.mapper.UserMapper;
import my.university.model.repository.RoleRepository;
import my.university.model.repository.UserRepository;
import my.university.model.service.UserService;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository mockUserRepository;
    @Mock
    private RoleRepository mockRoleRepository;
    @Mock
    private BCryptPasswordEncoder mockBCryptPasswordEncoder;
    @Mock
    private UserMapper userMapper;
    @InjectMocks
    private UserServiceImpl userServiceUnderTest;
    private UserEntity userEntity = UserEntity.newBuilder()
            .withId(1)
            .withFirstName("Gustavo")
            .withLastName("Ponce")
            .withEmail("test@test.com")
            .build();
    private User user = User.newBuilder()
            .withId(1)
            .withFirstName("Gustavo")
            .withLastName("Ponce")
            .withEmail("test@test.com")
            .build();
    Set<Role> roles = new HashSet<>();
    {
        roles.add(new Role("USER"));
    }
    private User userAfterRegistration = User.newBuilder()
            .withId(1)
            .withFirstName("Gustavo")
            .withLastName("Ponce")
            .withEmail("test@test.com")
            .withActive(1)
            .withRoles(roles)
            .build();
    @After
    public void resetMock() {
        reset(mockUserRepository, mockRoleRepository,userMapper, mockBCryptPasswordEncoder);
    }




    @Test
    public void shouldReturnUserByEmail() {
        when(mockUserRepository.findByEmail(anyString())).thenReturn(Optional.ofNullable(userEntity));
        when(userMapper.mapEntityToDomain(any(UserEntity.class))).thenReturn(user);
        when(mockBCryptPasswordEncoder.encode(any(String.class))).thenReturn(userEntity.getPassword());
        final String email = "test@test.com";

        final User result = userServiceUnderTest.findByEmail(email);

        assertEquals(email, result.getEmail());
    }

    @Test
    public void shouldReturnUserWhenRegistered() {
        when(mockRoleRepository.findByRole(any())).thenReturn(new Role("USER"));
        when(userMapper.mapEntityToDomain(any(UserEntity.class))).thenReturn(user);
        when(mockBCryptPasswordEncoder.encode(any(String.class))).thenReturn(userEntity.getPassword());


        User result = userServiceUnderTest.registration(user);

        assertEquals(userAfterRegistration, result);
    }
}