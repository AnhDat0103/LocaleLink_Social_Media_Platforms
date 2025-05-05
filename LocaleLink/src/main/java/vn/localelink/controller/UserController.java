package vn.localelink.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import vn.localelink.DTO.request.UserPatchUpdate;
import vn.localelink.DTO.request.UserPutUpdate;
import vn.localelink.DTO.request.UserRegister;
import vn.localelink.DTO.response.ApiResponse;
import vn.localelink.DTO.response.UserResponse;
import vn.localelink.exception.AppException;
import vn.localelink.service.UserService;

import java.util.List;

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

    @PostMapping
    public ApiResponse<UserResponse> createUser(@Valid @RequestBody UserRegister userRegister) throws AppException {
        UserResponse user = userService.createUser(userRegister);
        return ApiResponse.<UserResponse>builder()
                .status("success")
                .data(user)
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<UserResponse> updateUser(@PathVariable int id, @Valid @RequestBody UserPutUpdate userPutUpdate) throws AppException {
        UserResponse user = userService.updateUser(id, userPutUpdate);
        return ApiResponse.<UserResponse>builder()
                .status("success")
                .data(user)
                .build();
    }

    @PatchMapping("/{id}")
    public ApiResponse<UserResponse> partialUpdate(@PathVariable int id,@Valid @RequestBody UserPatchUpdate userPatchUpdate) throws AppException {
        UserResponse user = userService.partialUpdateUser(id, userPatchUpdate);
        return ApiResponse.<UserResponse>builder()
                .status("success")
                .data(user)
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteUser(@PathVariable int id) throws AppException {
        userService.deleteUser(id);
        return ApiResponse.builder()
                .status("success")
                .message("User deleted successfully")
                .build();
    }





}
