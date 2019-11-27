package my.university.model.service.impl;

import my.university.model.domain.ExamResult;
import my.university.model.entity.ExamResultEntity;
import my.university.model.mapper.CourseMapper;
import my.university.model.mapper.ExamResultMapper;
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

@Service
public class ExamResultServiceImpl implements ExamResultService {
    private final ExamResultRepository examResultRepository;

    private final ExamResultMapper examResultMapper;

    private final CourseMapper courseMapper;





    @Autowired
    public ExamResultServiceImpl(ExamResultRepository examResultRepository, ExamResultMapper examResultMapper, CourseMapper courseMapper) {
        this.examResultRepository = examResultRepository;
        this.examResultMapper = examResultMapper;
        this.courseMapper = courseMapper;
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
