package my.university.model.repository;

import my.university.model.entity.SpecialityEntity;
import my.university.model.entity.UserResultEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface UserResultRepository extends JpaRepository<UserResultEntity, Integer> {
    @Query(value = "SELECT  * From user_results where  id_user =?1", nativeQuery = true)
    Optional<UserResultEntity> findByUserId(Integer id);
    @Query( nativeQuery = true,
            value = "SELECT *" +
                    "From user_results" +
                    "         LEFT JOIN specialities s on user_results.speciality_id = s.speciality_id" +
                    "         LEFT JOIN users u on user_results.id_user = u.user_id where  s.speciality_id = ?")
    Page<UserResultEntity> findBySpecialityId (Integer specialityId, Pageable pageable);


}
