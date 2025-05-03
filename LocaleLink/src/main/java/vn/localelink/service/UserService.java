package vn.localelink.service;

import vn.localelink.DTO.request.UserRegister;
import vn.localelink.DTO.response.UserResponse;
import vn.localelink.exception.AppException;

import java.util.List;

public interface UserService {

    UserResponse findByEmail(String email);
    List<UserResponse> findAll();

    UserResponse findById(int id) throws AppException;

    UserResponse createUser(UserRegister userRegister) throws AppException;
}
