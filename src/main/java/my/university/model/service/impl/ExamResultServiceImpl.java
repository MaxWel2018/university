package my.university.model.service.impl;

import lombok.AllArgsConstructor;
import my.university.model.domain.ExamResult;
import my.university.model.entity.ExamResultEntity;
import my.university.model.service.mapper.ExamResultMapper;
import my.university.model.repository.ExamResultRepository;
import my.university.model.service.ExamResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class ExamResultServiceImpl implements ExamResultService {
    private final ExamResultRepository examResultRepository;

    private final ExamResultMapper examResultMapper;

    @Override
    public ExamResultEntity save(ExamResult examResult) {
        return Optional.ofNullable(examResult)
                .map(examResultMapper::mapDomainToEntity)
                .map(examResultRepository::save)
                .orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public List<ExamResult> findByUser(Integer id) {
        return Optional.ofNullable(id)
                .map(examResultRepository::findByUserId)
                .map(this::ExamResultList)
                .orElse(null);
    }

    @Override
    public Page<ExamResult> findByCourseIdAndDate(Integer id, LocalDate date, Pageable pageable) {
        return examResultRepository.findByCourseIdAndDate(id, date,pageable).map(examResultMapper::mapEntityToDomain);
    }


    private List<ExamResult> ExamResultList(List<ExamResultEntity> entity) {
        return entity.stream()
                .map(examResultMapper::mapEntityToDomain)
                .collect(Collectors.toList());
    }
}
