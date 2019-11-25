package my.university.model.service.impl;

import lombok.NoArgsConstructor;
import my.university.model.domain.User;
import my.university.model.entity.Role;
import my.university.model.exception.AuthorisationFailException;
import my.university.model.exception.EntityAlreadyExistException;
import my.university.model.exception.EntityNotFoundException;
import my.university.model.mapper.UserMapper;
import my.university.model.repository.RoleRepository;
import my.university.model.repository.UserRepository;
import my.university.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.*;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    private RoleRepository roleRepository;

    private BCryptPasswordEncoder passwordEncoder;

    private UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    @Override
    public User registration(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new EntityAlreadyExistException("A user with this Email is already registered");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role role = roleRepository.findByRole("USER");
        user.setActive(1);

        user.setRoles(new HashSet<Role>(Collections.singletonList(role)));
        userRepository.save(userMapper.mapDomainToEntity(user));

        return user;
    }

    @Override
    public User findByEmail(String email) {
        Objects.requireNonNull(email,"email empty");
        return userMapper.mapEntityToDomain(userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User with Email: [" + email + "] not found")));
    }

    @Override
    public User login(String email, String password) {
        User user = findByEmail(email);
        @NotEmpty(message = "Please provide a password") @Pattern(regexp = "[A-Za-zA-Яа-яёЁ!_#$%^&*()-=+-]{2,32}")
        String userPassword = user.getPassword();
        if (userPassword.equals(passwordEncoder.encode(password))) {
            return user;
        } else {
            throw new AuthorisationFailException("Email or Password dont correct");
        }
    }

    @Override
    public User findById(Integer id) {
        return userMapper.mapEntityToDomain(userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with id[" + id + "] dont found")));
    }

    @Override
    public void update(User user) {
        findById(user.getId());
        registration(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository
                .findAll().stream()
                .map(userMapper::mapEntityToDomain)
                .collect(Collectors.toList());
    }
}