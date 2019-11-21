package my.university.model.service.impl;

import lombok.NoArgsConstructor;
import my.university.exception.AuthorisationFailException;
import my.university.exception.EntityAlreadyExistException;
import my.university.exception.EntityNotFoundException;
import my.university.mapper.UserMapper;
import my.university.model.domain.User;
import my.university.model.entity.Role;
import my.university.model.repository.UserRepository;
import my.university.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder;

    private UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    @Override
    public User registration(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new EntityAlreadyExistException("A user with this Email is already registered");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(new Role("USER"));
        userRepository.save(userMapper.mapDomainToEntity(user));

        return null;
    }

    @Override
    public User findByEmail(String email) {
        Objects.requireNonNull(email);
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
        findByEmail(user.getEmail());
        userRepository.save(userMapper.mapDomainToEntity(user));
    }

    @Override
    public List<User> findAll() {
        return userRepository
                .findAll().stream()
                .map(userMapper::mapEntityToDomain)
                .collect(Collectors.toList());
    }
}
