package my.university.model.repository;

import my.university.model.entity.UserResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface UserResultRepository extends JpaRepository<UserResultEntity, Integer> {

}
