package vn.localelink.controller;

import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.localelink.DTO.response.ApiResponse;
import vn.localelink.DTO.response.UserResponse;
import vn.localelink.exception.AppException;
import vn.localelink.service.UserService;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public ApiResponse<List<UserResponse>> getAllUser(){
        List<UserResponse> users = userService.findAll();
        return ApiResponse.<List<UserResponse>>builder()
                .status("success")
                .data(users)
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<UserResponse> getUserById(@PathVariable int id) throws AppException {
        UserResponse user = userService.findById(id);
        return ApiResponse.<UserResponse>builder()
                .status("success")
                .data(user)
                .build();
    }






}
