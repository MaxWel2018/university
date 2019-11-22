package my.university.model.mapper;

import my.university.model.domain.Speciality;
import my.university.model.entity.SpecialityEntity;

public interface SpecialityMapper {

    Speciality mapEntityToDomain(SpecialityEntity specialityEntity);

    SpecialityEntity mapDomainToEntity(Speciality speciality);

}
