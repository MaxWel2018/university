package my.university.model.repository;

import my.university.model.entity.ExamResultEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExamResultRepository extends JpaRepository<ExamResultEntity, Integer>{

    @Query(value = "Select *From exam_results left join  courses sc on exam_results.id_course = sc.course_id where exam_results.id_user = ?1", nativeQuery = true)
    List<ExamResultEntity> findByUserId(Integer id);

}
