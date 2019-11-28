package my.university.model.service;

import my.university.model.domain.UserResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserResultService {

    Page<UserResult> findBySpecialityId (Integer specialityId, Pageable pageable);

}
