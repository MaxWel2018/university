package my.university.model.service.impl;

import lombok.AllArgsConstructor;
import my.university.model.domain.ExamResult;
import my.university.model.domain.User;
import my.university.model.entity.ExamResultEntity;
import my.university.model.repository.ExamResultRepository;
import my.university.model.service.ExamResultService;
import my.university.model.service.UserService;
import my.university.model.service.mapper.ExamResultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class ExamResultServiceImpl implements ExamResultService {
    private final ExamResultRepository examResultRepository;

    private final UserService userService;

    private final ExamResultMapper examResultMapper;

    @Override
    public ExamResultEntity save(ExamResult examResult) {
        return Optional.ofNullable(examResult)
                .map(examResultMapper::mapDomainToEntity)
                .map(examResultRepository::save)
                .orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public List<ExamResult> findByUserId(Integer id) {
        return Optional.ofNullable(id)
                .map(examResultRepository::findByUserId)
                .map(this::ExamResultList)
                .orElse(null);
    }

    @Override
    public ExamResult update(ExamResult examResult) {
        validateGrade(examResult);
        addUser(examResult);
        examResultRepository.save(examResultMapper.mapDomainToEntity(examResult));
        return examResult;
    }

    private void addUser(ExamResult examResult) {
        User user = userService.findById(examResult.getUser().getId());
        examResult.setUser(user);
    }

    @Override
    public List<ExamResult> findAll() {
        return examResultRepository.findAll()
                .stream()
                .map(examResultMapper::mapEntityToDomain)
                .collect(Collectors.toList());
    }

    private List<ExamResult> ExamResultList(List<ExamResultEntity> entity) {
        return entity.stream()
                .map(examResultMapper::mapEntityToDomain)
                .collect(Collectors.toList());
    }

    private void validateGrade(ExamResult examResult) {
        if (examResult.getGrade() > 100 || examResult.getGrade() < 0) {
            throw new IllegalArgumentException("In correct grade");
        }
    }
}
