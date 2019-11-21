package my.university.model.service;

import my.university.model.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    User registration(User user);

    User findByEmail(String email);

    User login(String email, String password);

    User findById(Integer id);

    void update(User entity);

    List<User> findAll();
}
