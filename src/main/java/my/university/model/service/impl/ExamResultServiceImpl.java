package my.university.model.service.impl;

import my.university.model.domain.ExamResult;
import my.university.model.entity.ExamResultEntity;
import my.university.model.mapper.ExamResultMapper;
import my.university.model.repository.ExamResultRepository;
import my.university.model.service.ExamResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
