package my.university.model.service;

import my.university.model.domain.ExamResult;
import my.university.model.domain.Speciality;
import my.university.model.domain.User;
import my.university.model.domain.UserResult;
import my.university.model.entity.SpecialityEntity;
import my.university.model.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    User registration(User user);

    User findByEmail(String email);

    User findById(Integer id);

    UserResult findFinalResultByUserId(Integer id);

    Page<User> findBySpeciality(Integer specialityId, Pageable pageable);

    void update(User user);


}
