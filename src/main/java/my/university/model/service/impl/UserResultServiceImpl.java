package my.university.model.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import my.university.model.domain.ExamResult;
import my.university.model.domain.Speciality;
import my.university.model.domain.User;
import my.university.model.domain.UserResult;
import my.university.model.entity.UserResultEntity;
import my.university.model.repository.UserResultRepository;
import my.university.model.service.SpecialityService;
import my.university.model.service.UserResultService;
import my.university.model.service.mapper.UserResultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserResultServiceImpl implements UserResultService {

    private final UserResultRepository userResultRepository;

    private final SpecialityService specialityService;

    private final UserResultMapper userResultMapper;

    @Override
    public Page<UserResult> findBySpecialityId(Integer specialityId, Pageable pageable) {
        if (specialityId == null || pageable == null) {
           log.info("speciality id = " + specialityId + " and pageable == " + pageable);
            return null;
        }
        return userResultRepository.findBySpecialityId(specialityId, pageable)
                .map(userResultMapper::mapEntityToDomain);
    }

    @Override
    public List<UserResult> convertExamResultsToUserResults(List<ExamResult> examResults) {
        if (examResults == null || examResults.isEmpty()) {
            log.info("examResult = " + examResults);
            return null;
        }
        List<UserResult> userResults = new ArrayList<>();
        Map<User, List<ExamResult>> userIdToExamResult = examResults.stream()
                .collect(Collectors.groupingBy(ExamResult::getUser));

        for (User user : userIdToExamResult.keySet()) {
            if("ADMIN".equalsIgnoreCase(user.getRoles().toString())){
                continue;
            }
            userResults.add(createUserResult(user, userIdToExamResult.get(user)));
        }

        return userResults;
    }

    @Override
    public void save(UserResult userResult) {
        if (userResult == null) {
            log.info("User result == null");
            throw new IllegalArgumentException("User result == null");
        }
        Optional<UserResultEntity> userResultFromDataBase = userResultRepository.findByUserId(userResult.getUser().getId());
        userResultFromDataBase.ifPresent(userResultEntity -> userResult.setId(userResultEntity.getId()));

        UserResultEntity userResultEntity = userResultMapper.mapDomainToEntity(userResult);
        userResultRepository.save(userResultEntity);
    }

    @Override
    public void saveAll(List<UserResult> userResults) {
        for (UserResult userResult : userResults) {
            save(userResult);
        }
    }

    @Override
    public void enrollUsersByIdSpeciality(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Id incorrect");
        }
        Speciality byId = specialityService.findById(id);
        List<UserResultEntity> bySpecialityId = userResultRepository.findBySpecialityId(id);
        for (int i = 0; i < byId.getStudentsNumber(); i++) {
            bySpecialityId.get(i).setConfirmed(true);
            userResultRepository.save(bySpecialityId.get(i));
        }

    }


    private UserResult createUserResult(User user, List<ExamResult> examResult) {
        return UserResult.newBuilder()
                .withUser(user)
                .withSpeciality(user.getSpeciality())
                .withFinalMark(calculateFinalGrade(examResult))
                .build();

    }

    private Integer calculateFinalGrade(List<ExamResult> examResults) {
        return examResults.stream().map(ExamResult::getGrade).reduce(0, Integer::sum);
    }
}
