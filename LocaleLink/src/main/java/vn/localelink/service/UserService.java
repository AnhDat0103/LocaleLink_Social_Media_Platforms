package vn.localelink.service;

import jakarta.validation.Valid;
import vn.localelink.DTO.request.UserPatchUpdate;
import vn.localelink.DTO.request.UserPutUpdate;
import vn.localelink.DTO.request.UserRegister;
import vn.localelink.DTO.response.UserResponse;
import vn.localelink.entity.User;
import vn.localelink.exception.AppException;

import java.util.List;

public interface UserService {
    List<UserResponse> findAll();

    UserResponse findById(int id) throws AppException;

    UserResponse createUser(UserRegister userRegister) throws AppException;

    void deleteUser(int id) throws AppException;

    UserResponse updateUser(int id, @Valid UserPutUpdate userPutUpdate) throws AppException;

    UserResponse partialUpdateUser(int id, UserPatchUpdate userPatchUpdate) throws AppException;

    User findByEmail(String email) throws AppException;
}
