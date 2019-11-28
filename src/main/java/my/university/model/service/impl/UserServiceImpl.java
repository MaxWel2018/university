package my.university.model.service.impl;

import lombok.NoArgsConstructor;
import my.university.model.domain.User;
import my.university.model.domain.UserResult;
import my.university.model.entity.Role;
import my.university.model.exception.EntityAlreadyExistException;
import my.university.model.exception.EntityNotFoundException;
import my.university.model.mapper.UserMapper;
import my.university.model.mapper.UserResultMapper;
import my.university.model.repository.RoleRepository;
import my.university.model.repository.UserRepository;
import my.university.model.repository.UserResultRepository;
import my.university.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;

@Service
@NoArgsConstructor
public class UserServiceImpl implements UserService {

    public static final int ACTIVE = 1;
    private  UserRepository userRepository;

    private UserResultRepository userResultRepository;

    private RoleRepository roleRepository;

    private BCryptPasswordEncoder passwordEncoder;

    private UserMapper userMapper;

    private UserResultMapper userResultMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserResultRepository userResultRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder, UserMapper userMapper, UserResultMapper userResultMapper) {
        this.userRepository = userRepository;
        this.userResultRepository = userResultRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
        this.userResultMapper = userResultMapper;
    }

    @Override
    public User registration(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new EntityAlreadyExistException("A user with this Email is already registered");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(ACTIVE);
        checkRoles(user);
        userRepository.save(userMapper.mapDomainToEntity(user));

        return user;
    }

    @Override
    public User findByEmail(String email) {
        return Optional.ofNullable(email)
                .map(Objects::requireNonNull)
                .map(userRepository::findByEmail)
                .get()
                .map(userMapper::mapEntityToDomain)
                .orElseThrow(() -> new EntityNotFoundException("User with Email: [" + email + "] not found"));
    }


    @Override
    public User findById(Integer id) {
        return Optional.ofNullable(id)
                .map(Objects::requireNonNull)
                .map(userRepository::findById)
                .get()
                .map(userMapper::mapEntityToDomain)
                .orElseThrow(() -> new EntityNotFoundException("User with id[" + id + "] dont found"));
    }

    @Override
    public void update(User user) {
        findById(user.getId());
        registration(user);
    }

    @Override
    public UserResult findFinalResultByUserId(Integer id) {
        return Optional.ofNullable(id)
                .map(Objects::requireNonNull)
                .map(userResultRepository::findByUserId)
                .map(userResultMapper::mapEntityToDomain)
                .orElse(null);

    }

    private void checkRoles(User user) {
        if (user.getRoles() == null) {
            Role role = roleRepository.findByRole("USER");
            user.setRoles(Collections.singleton(role));
        }
    }
}