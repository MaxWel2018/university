package my.university.model.service.impl;

import my.university.model.domain.ExamResult;
import my.university.model.entity.ExamResultEntity;
import my.university.model.mapper.ExamResultMapper;
import my.university.model.repository.ExamResultRepository;
import my.university.model.service.ExamResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExamResultServiceImpl implements ExamResultService {
    private final ExamResultRepository examResultRepository;

    private final ExamResultMapper examResultMapper;


    @Autowired
    public ExamResultServiceImpl(ExamResultRepository examResultRepository, ExamResultMapper examResultMapper) {
        this.examResultRepository = examResultRepository;
        this.examResultMapper = examResultMapper;
    }

    @Override
    public ExamResultEntity save(ExamResult examResult) {
      return   examResultRepository.save(examResultMapper.mapDomainToEntity(examResult));
    }

    @Override
    public List<ExamResult> findByUser(Integer id) {
        return Optional.ofNullable(id)
                .map(examResultRepository::findByUserId)
                .map(this::ExamResultList)
                .orElse(null);
    }

    private List<ExamResult> ExamResultList(List<ExamResultEntity> entity) {
        return entity.stream()
                .map(examResultMapper::mapEntityToDomain)
                .collect(Collectors.toList());
    }
}
