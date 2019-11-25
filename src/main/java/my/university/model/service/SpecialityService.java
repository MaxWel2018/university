package my.university.model.service;

import my.university.model.domain.Speciality;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface SpecialityService {
    List<Speciality> findAll();

    Speciality findById(Integer id);

    Long count();
}
