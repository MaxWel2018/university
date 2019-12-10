package my.university.model.repository;

import my.university.model.entity.SpecialityEntity;
import my.university.model.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecialityRepository extends JpaRepository<SpecialityEntity, Integer> {
}
