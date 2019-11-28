package my.university.model.service.impl;

import my.university.model.domain.User;
import my.university.model.entity.Role;
import my.university.model.entity.UserEntity;
import my.university.model.service.mapper.UserMapper;
import my.university.model.repository.RoleRepository;
import my.university.model.repository.UserRepository;
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

        assertThat(email, is(equalTo(result.getEmail())));
    }

    @Test
    public void shouldReturnUserById() {
        when(mockUserRepository.findById(anyInt())).thenReturn(Optional.ofNullable(userEntity));
        when(userMapper.mapEntityToDomain(any(UserEntity.class))).thenReturn(user);
        when(mockBCryptPasswordEncoder.encode(any(String.class))).thenReturn(userEntity.getPassword());

        final User result = userServiceUnderTest.findById(user.getId());

        assertThat(user.getId(), is(equalTo(result.getId())));
    }

    @Test
    public void shouldReturnUserWhenRegistered() {
        when(mockRoleRepository.findByRole(any())).thenReturn(new Role("USER"));
        when(userMapper.mapEntityToDomain(any(UserEntity.class))).thenReturn(user);
        when(mockBCryptPasswordEncoder.encode(any(String.class))).thenReturn(userEntity.getPassword());

        User result = userServiceUnderTest.registration(user);

        assertThat(userAfterRegistration, is(equalTo(result)));
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