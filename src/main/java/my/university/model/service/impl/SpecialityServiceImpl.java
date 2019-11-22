package my.university.model.service.impl;

import lombok.NoArgsConstructor;
import my.university.model.domain.Speciality;
import my.university.model.exception.EntityNotFoundException;
import my.university.model.mapper.SpecialityMapper;
import my.university.model.repository.SpecialityRepository;
import my.university.model.service.SpecialityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
public class SpecialityServiceImpl implements SpecialityService {

    private SpecialityRepository specialityRepository;

    private SpecialityMapper specialityMapper;

    @Autowired
    public SpecialityServiceImpl(SpecialityRepository specialityRepository, SpecialityMapper specialityMapper) {
        this.specialityRepository = specialityRepository;
        this.specialityMapper = specialityMapper;
    }

    public List<Speciality> findAll() {
        return specialityRepository.findAll().stream()
                .map(specialityMapper::mapEntityToDomain).collect(Collectors.toList());
    }

    @Override
    public Speciality findById(Integer id) {
        return specialityMapper.mapEntityToDomain(specialityRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Speciality with " + id + " not found")));
    }

}
