package my.university.model.mapper;

import my.university.model.domain.User;
import my.university.model.entity.UserEntity;

@org.mapstruct.Mapper(componentModel = "spring")
public interface UserMapper extends Mapper<User, UserEntity> {
}
