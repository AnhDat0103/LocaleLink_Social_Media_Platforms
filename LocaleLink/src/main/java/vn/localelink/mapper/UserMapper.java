package vn.localelink.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import vn.localelink.DTO.request.UserPutUpdate;
import vn.localelink.DTO.request.UserRegister;
import vn.localelink.DTO.response.UserResponse;
import vn.localelink.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponse userToUserResponse(User user);

    User userRegisterToUser(UserRegister userRegister);

    User userUpdateToUser(@MappingTarget User user, UserPutUpdate userPutUpdate);
}
