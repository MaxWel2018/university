package my.university.mapper;

import my.university.model.domain.User;
import my.university.model.entity.UserEntity;

@org.mapstruct.Mapper
public interface UserMapper extends Mapper<User, UserEntity> {
}
