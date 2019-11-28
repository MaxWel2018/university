package my.university.model.service.impl;

import lombok.AllArgsConstructor;
import my.university.model.domain.UserResult;
import my.university.model.service.mapper.UserResultMapper;
import my.university.model.repository.UserResultRepository;
import my.university.model.service.UserResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserResultServiceImpl implements UserResultService {

    private final UserResultRepository userResultRepository;

    private final UserResultMapper userResultMapper;

    @Override
    public Page<UserResult> findBySpecialityId(Integer specialityId, Pageable pageable) {
        if (specialityId == null || pageable == null) {
            return null;
        }
        return userResultRepository.findBySpecialityId(specialityId, pageable)
                .map(userResultMapper::mapEntityToDomain);
    }
}
