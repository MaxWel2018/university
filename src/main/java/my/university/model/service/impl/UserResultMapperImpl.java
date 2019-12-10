package my.university.model.service.impl;

import lombok.AllArgsConstructor;
import my.university.model.domain.Speciality;
import my.university.model.domain.User;
import my.university.model.domain.UserResult;
import my.university.model.entity.SpecialityEntity;
import my.university.model.entity.UserEntity;
import my.university.model.entity.UserResultEntity;
import my.university.model.service.mapper.SpecialityMapper;
import my.university.model.service.mapper.UserMapper;
import my.university.model.service.mapper.UserResultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserResultMapperImpl implements UserResultMapper {
    private final UserMapper userMapper;

    private final SpecialityMapper specialityMapper;

    @Override
    public UserResult mapEntityToDomain(UserResultEntity userResultEntity) {
        if (userResultEntity == null)
            return UserResult.newBuilder().build();
        return UserResult.newBuilder()
                .withId(userResultEntity.getId())
                .withConfirmed(userResultEntity.getConfirmed())
                .withFinalMark(userResultEntity.getFinalMark())
                .withUser(getUser(userResultEntity))
                .withSpeciality(getSpeciality(userResultEntity))
                .build();
    }


    @Override
    public UserResultEntity mapDomainToEntity(UserResult userResult) {
        if (userResult == null) {
            return UserResultEntity.newBuilder().build();
        }
        return UserResultEntity.newBuilder()
                .withId(userResult.getId())
                .withConfirmed(userResult.getConfirmed())
                .withFinalMark(userResult.getFinalMark())
                .withUserEntity(getUserEntity(userResult))
                .withSpecialityEntity(getSpecialityEntity(userResult))
                .build();


    }

    private SpecialityEntity getSpecialityEntity(UserResult userResult) {
        return Optional.ofNullable(userResult.getSpeciality()).map(specialityMapper::mapDomainToEntity).orElse(null);
    }

    private UserEntity getUserEntity(UserResult userResult) {
        return Optional.ofNullable(userResult.getUser()).map(userMapper::mapDomainToEntity).orElse(null);
    }

    private Speciality getSpeciality(UserResultEntity userResultEntity) {
        return Optional.ofNullable(userResultEntity.getSpecialityEntity())
                .map(specialityMapper::mapEntityToDomain)
                .orElse(null);
    }

    private User getUser(UserResultEntity userResultEntity) {
        return Optional.ofNullable(userResultEntity.getUserEntity())
                .map(userMapper::mapEntityToDomain)
                .orElse(null);
    }

}
