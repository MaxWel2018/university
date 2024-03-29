package my.university.model.service.mapper.impl;

import lombok.AllArgsConstructor;
import my.university.model.domain.Speciality;
import my.university.model.domain.User;
import my.university.model.entity.SpecialityEntity;
import my.university.model.entity.UserEntity;
import my.university.model.service.mapper.SpecialityMapper;
import my.university.model.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Component
public class UserMapperImpl implements UserMapper {

    private final SpecialityMapper specialityMapper;

    @Override
    public UserEntity mapDomainToEntity(User user) {
        return user == null ? null : mapUserEntityToUser(user);
    }

    private UserEntity mapUserEntityToUser(User user) {
        return UserEntity.newBuilder()
                .withId(user.getId())
                .withRoles(user.getRoles())
                .withActive(user.getActive())
                .withEmail(user.getEmail())
                .withLastName(user.getLastName())
                .withPassword(user.getPassword())
                .withFirstName(user.getFirstName())
                .withSpecialityEntity(getSpecialityEntity(user)).build();
    }

    @Override
    public User mapEntityToDomain(UserEntity userEntity) {
        return userEntity == null ? null : mapUserEntityToUser(userEntity);
    }

    private User mapUserEntityToUser(UserEntity userEntity) {
        return User.newBuilder()
                .withId(userEntity.getId())
                .withRoles(userEntity.getRoles())
                .withActive(userEntity.getActive())
                .withEmail(userEntity.getEmail())
                .withLastName(userEntity.getLastName())
                .withPassword(userEntity.getPassword())
                .withFirstName(userEntity.getFirstName())
                .withSpeciality(getSpeciality(userEntity))
                .build();
    }

    private Speciality getSpeciality(UserEntity userEntity) {
        return Optional.ofNullable(userEntity.getSpecialityEntity())
                .map(specialityMapper::mapEntityToDomain)
                .orElse(null);
    }

    private SpecialityEntity getSpecialityEntity(User user) {
        return Optional.ofNullable(user.getSpeciality())
                .map(specialityMapper::mapDomainToEntity)
                .orElse(null);
    }
}
