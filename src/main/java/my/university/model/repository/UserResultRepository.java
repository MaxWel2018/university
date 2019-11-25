package my.university.model.repository;

import my.university.model.entity.UserResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository

public interface UserResultRepository extends JpaRepository<UserResultEntity, Integer> {
    @Query(value = "SELECT  * From user_results where  id_user =?1", nativeQuery = true)
    UserResultEntity findByUserId(Integer id);
}
