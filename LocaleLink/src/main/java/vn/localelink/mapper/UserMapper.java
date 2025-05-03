package vn.localelink.mapper;

import org.mapstruct.Mapper;
import vn.localelink.DTO.response.UserResponse;
import vn.localelink.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponse userToUserResponse(User user);
}
