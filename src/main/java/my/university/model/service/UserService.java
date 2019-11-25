package my.university.model.service;

import my.university.model.domain.User;
import my.university.model.domain.UserResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    User registration(User user);

    User findByEmail(String email);


    User findById(Integer id);

    void update(User entity);

    List<User> findAll();

    UserResult findFinalResultByUserId(Integer id);
}
