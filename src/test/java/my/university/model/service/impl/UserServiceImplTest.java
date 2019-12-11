package my.university.model.service.impl;

import my.university.model.domain.User;
import my.university.model.entity.Role;
import my.university.model.entity.UserEntity;
import my.university.model.exception.EntityAlreadyExistException;
import my.university.model.exception.EntityNotFoundException;
import my.university.model.repository.RoleRepository;
import my.university.model.repository.UserRepository;
import my.university.model.service.mapper.UserMapper;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
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
    private UserEntity userEntity = getUserEntity();

    private User user = getUser();

    private Set<Role> roles = new HashSet<>();
    {
        roles.add(new Role("USER"));
    }
    private User userAfterRegistration = getUserAfterRegistration();

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

        assertThat("User dont found",email, is(equalTo(result.getEmail())));
    }

    @Test
    public void shouldReturnUserById() {
        when(mockUserRepository.findById(anyInt())).thenReturn(Optional.ofNullable(userEntity));
        when(userMapper.mapEntityToDomain(any(UserEntity.class))).thenReturn(user);
        when(mockBCryptPasswordEncoder.encode(any(String.class))).thenReturn(userEntity.getPassword());

        final User result = userServiceUnderTest.findById(user.getId());

        assertThat("User dont found",user.getId(), is(equalTo(result.getId())));
    }

    @Test
    public void shouldReturnUserWhenRegistered() {
        when(mockRoleRepository.findByRole(any())).thenReturn(new Role("USER"));
        when(userMapper.mapEntityToDomain(any(UserEntity.class))).thenReturn(user);
        when(mockBCryptPasswordEncoder.encode(any(String.class))).thenReturn(userEntity.getPassword());

        User result = userServiceUnderTest.registration(user);

        assertThat("registered failed ", userAfterRegistration, is(equalTo(result)));
    }

    @Test
    public void shouldThrowExceptionIfUserAlreadyRegistered() {
        when(mockRoleRepository.findByRole(any())).thenReturn(new Role("USER"));
        when(userMapper.mapEntityToDomain(any(UserEntity.class))).thenReturn(user);
        when(mockBCryptPasswordEncoder.encode(any(String.class))).thenReturn(userEntity.getPassword());
        when(mockUserRepository.findByEmail(anyString())).thenReturn(Optional.ofNullable(userEntity));

        EntityAlreadyExistException exception = assertThrows(
                EntityAlreadyExistException.class,
                () -> {
                    userServiceUnderTest.registration(user);
                }
        );
        assertThat("expected my.university.model.exception.EntityAlreadyExistException to be thrown, but nothing was thrown"
                , EntityAlreadyExistException.class,
                is(equalTo(exception.getClass())));
        assertThat("massages not equal",
                "A user with this Email is already registered", is(equalTo(exception.getMessage())));
    }

    @Test
    public void shouldThrowExceptionIfUserNotFoundByEmail() {
        when(mockRoleRepository.findByRole(any())).thenReturn(new Role("USER"));
        when(userMapper.mapEntityToDomain(any(UserEntity.class))).thenReturn(user);
        when(mockBCryptPasswordEncoder.encode(any(String.class))).thenReturn(userEntity.getPassword());
        when(mockUserRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(
                EntityNotFoundException.class,
                () -> {
                    userServiceUnderTest.findByEmail("HelloWorld@gmail.com");
                }
        );
        assertThat("expected my.university.model.exception.EntityNotFoundException to be thrown, but nothing was thrown"
                , EntityNotFoundException.class,
                is(equalTo(exception.getClass())));
        assertThat("massages not equal",
                "User with Email: [HelloWorld@gmail.com] not found", is(equalTo(exception.getMessage())));
    }

    @Test
    public void shouldThrowExceptionIfUserNotFoundById() {
        when(mockRoleRepository.findByRole(any())).thenReturn(new Role("USER"));
        when(userMapper.mapEntityToDomain(any(UserEntity.class))).thenReturn(user);
        when(mockBCryptPasswordEncoder.encode(any(String.class))).thenReturn(userEntity.getPassword());
        when(mockUserRepository.findById(anyInt())).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(
                EntityNotFoundException.class,
                () -> {
                    userServiceUnderTest.findByEmail("10");
                }
        );
        assertThat("expected my.university.model.exception.EntityNotFoundException to be thrown, but nothing was thrown"
                , EntityNotFoundException.class,
                is(equalTo(exception.getClass())));
        assertThat("massages not equal",
                "User with Email: [10] not found", is(equalTo(exception.getMessage())));
    }


    private static UserEntity getUserEntity() {
        return UserEntity.newBuilder()
                .withId(1)
                .withFirstName("Gustavo")
                .withLastName("Ponce")
                .withEmail("test@test.com")
                .build();
    }

    private static User getUser() {
        return User.newBuilder()
                .withId(1)
                .withFirstName("Gustavo")
                .withLastName("Ponce")
                .withEmail("test@test.com")
                .build();
    }

    private User getUserAfterRegistration() {
        return User.newBuilder()
                .withId(1)
                .withFirstName("Gustavo")
                .withLastName("Ponce")
                .withEmail("test@test.com")
                .withActive(1)
                .withRoles(roles)
                .build();
    }
}