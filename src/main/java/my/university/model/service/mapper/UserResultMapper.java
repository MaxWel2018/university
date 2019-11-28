package my.university.model.service.mapper;

import my.university.model.domain.UserResult;
import my.university.model.entity.UserResultEntity;

@org.mapstruct.Mapper(componentModel = "spring")
public interface UserResultMapper extends Mapper<UserResult, UserResultEntity> {
}
