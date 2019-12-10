package my.university.model.repository;

import my.university.model.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    Optional<UserEntity> findByEmail(String email);

    @Query(nativeQuery = true,
            value = "SELECT * " +
                    "FROM users " +
                    " LEFT JOIN specialities sc on users.id_speciality = sc.speciality_id " +
                    "where sc.speciality_id = ?1")
    Page<UserEntity> findUserBySpecialityId(Integer id, Pageable pageable);

}
