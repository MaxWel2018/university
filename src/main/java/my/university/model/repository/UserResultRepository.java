package my.university.model.repository;

import my.university.model.entity.UserResultEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface UserResultRepository extends JpaRepository<UserResultEntity, Integer> {
    @Query(value = "SELECT  * From user_results where  id_user =?1", nativeQuery = true)
    Optional<UserResultEntity> findByUserId(Integer id);

    @Query( nativeQuery = true,
            value = "SELECT * " +
                    "From user_results ur" +
                    "  LEFT JOIN specialities s on s.speciality_id = ur.speciality_id" +
                    "  LEFT JOIN users u on u.user_id  = ur.id_user where  s.speciality_id = ?1")
    Page<UserResultEntity> findBySpecialityId (Integer specialityId, Pageable pageable);

    @Query(nativeQuery = true,
            value = "SELECT * " +
                    "From user_results ur" +
                    "  LEFT JOIN specialities s on s.speciality_id = ur.speciality_id" +
                    "  LEFT JOIN users u on u.user_id  = ur.id_user where  s.speciality_id = ?1 ORDER BY final_mark DESC ")
    List<UserResultEntity> findBySpecialityId(Integer specialityId);


}
