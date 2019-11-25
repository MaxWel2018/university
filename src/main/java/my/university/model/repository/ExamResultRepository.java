package my.university.model.repository;

import my.university.model.domain.Course;
import my.university.model.entity.ExamResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;

@Repository
public interface ExamResultRepository extends JpaRepository<ExamResultEntity, Integer> {
}
