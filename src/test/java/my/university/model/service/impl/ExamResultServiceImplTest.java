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
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class ExamResultServiceImplTest {
    @Mock
    private ExamResultRepository examResultRepository;
    @Mock
    private ExamResultMapper examResultMapper;
    @InjectMocks
    private ExamResultServiceImpl examResultService;

    private final ExamResult examResult = getExamResult();

    private final ExamResultEntity examResultEntity = getExamResultEntity();

    private final List<ExamResult> examResults = Arrays.asList(examResult, examResult, examResult);

    private final List<ExamResultEntity> examResultsEntity = Arrays.asList(examResultEntity, examResultEntity, examResultEntity);

    @After
    public void resetMock() {
        reset(examResultRepository, examResultMapper);

    }

    @Test
    public void shouldReturnExamResultWhenSaveExamResult() {
        when(examResultRepository.save(any(ExamResultEntity.class))).thenReturn(examResultEntity);
        when(examResultMapper.mapDomainToEntity(any(ExamResult.class))).thenReturn(examResultEntity);

        ExamResultEntity examResultEntity = examResultService.save(examResult);

        assertThat("Exam result  not save ",this.examResultEntity, is(equalTo(examResultEntity)));

    }

    @Test
    public void shouldReturnListExamResultWhenFinByUserId() {
        when(examResultRepository.findByUserId(anyInt())).thenReturn(examResultsEntity);
        when(examResultMapper.mapEntityToDomain(any(ExamResultEntity.class))).thenReturn(examResult);

        List<ExamResult> examResults = examResultService.findByUserId(1);

        verify(examResultMapper, times(3)).mapEntityToDomain(any(ExamResultEntity.class));

        assertThat("Size list not equal  ",this.examResults.size(), is(equalTo(examResults.size())));
        for (int i = 0; i < examResults.size(); i++) {
            assertThat("Exam result index" +  i + "  dont equal ",examResults.get(i),
                    is(equalTo(this.examResults.get(i))));
        }

    }
 @Test
    public void shouldReturnNullWhenParameterFinByUserIdEqualNull() {
        when(examResultRepository.findByUserId(anyInt())).thenReturn(examResultsEntity);
        when(examResultMapper.mapEntityToDomain(any(ExamResultEntity.class))).thenReturn(examResult);

        List<ExamResult> examResults = examResultService.findByUserId(null);

        verify(examResultMapper, times(0)).mapEntityToDomain(any(ExamResultEntity.class));

        assertThat("Exam result not null ",examResults, is(nullValue()));

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