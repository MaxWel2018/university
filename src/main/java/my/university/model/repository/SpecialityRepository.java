package my.university.model.repository;

import my.university.model.entity.SpecialityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecialityRepository extends JpaRepository<SpecialityEntity, Integer> {
}
