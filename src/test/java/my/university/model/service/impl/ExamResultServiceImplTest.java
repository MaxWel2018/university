package my.university.model.service.impl;

import my.university.model.domain.Course;
import my.university.model.domain.ExamResult;
import my.university.model.domain.User;
import my.university.model.entity.CourseEntity;
import my.university.model.entity.ExamResultEntity;
import my.university.model.entity.UserEntity;
import my.university.model.service.mapper.ExamResultMapper;
import my.university.model.repository.ExamResultRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class ExamResultServiceImplTest {
    @Mock
    private ExamResultRepository examResultRepository;
    @Mock
    private ExamResultMapper examResultMapper;
    @InjectMocks
    private ExamResultServiceImpl examResultService;

    private ExamResult examResult = getExamResult();

    private ExamResultEntity examResultEntity = getExamResultEntity();

    @After
    public void resetMock() {
        reset(examResultRepository, examResultMapper);

    }

    @Test
    public void shouldReturnExamResultWhenSaveExamResult() {
        when(examResultRepository.save(any(ExamResultEntity.class))).thenReturn(examResultEntity);
        when(examResultMapper.mapDomainToEntity(any(ExamResult.class))).thenReturn(examResultEntity);

        ExamResultEntity examResultEntity = examResultService.save(examResult);

        assertThat(this.examResultEntity, is(equalTo(examResultEntity)));

    }

    private ExamResult getExamResult() {
        return ExamResult.newBuilder()
                .withUser(User.newBuilder().withId(1).build())
                .withDate(LocalDate.parse("2019-12-12"))
                .withGrade(99)
                .withId(1)
                .withCourse(new Course(1, "Math"))
                .build();
    }

    private ExamResultEntity getExamResultEntity() {
        return ExamResultEntity.newBuilder()
                .withUser(UserEntity.newBuilder().withId(1).build())
                .withDate(LocalDate.parse("2019-12-12"))
                .withGrade(99)
                .withId(1)
                .withCourse(new CourseEntity("Math"))
                .build();
    }
}