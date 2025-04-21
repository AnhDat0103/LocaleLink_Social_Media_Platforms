package vn.localelink.service;

import vn.localelink.dto.response.UserResponse;
import vn.localelink.entity.User;

import java.util.List;

public interface UserService {

    UserResponse findByEmail(String email);
    List<UserResponse> findAll();
}
