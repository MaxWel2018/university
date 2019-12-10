package my.university.model.service;

import my.university.model.domain.ExamResult;
import my.university.model.domain.UserResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserResultService {

    Page<UserResult> findBySpecialityId (Integer specialityId, Pageable pageable);

    List<UserResult> convertExamResultsToUserResults(List<ExamResult> examResults);

    void save(UserResult userResult);

    void saveAll(List<UserResult> userResults);

    void enrollUsersByIdSpeciality(Integer id);
}
