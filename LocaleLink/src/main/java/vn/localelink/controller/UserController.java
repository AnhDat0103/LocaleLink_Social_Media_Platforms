package vn.localelink.controller;

import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.localelink.DTO.response.UserResponse;
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
    public List<UserResponse> getAllUser(Model model){
        List<UserResponse> users = userService.findAll();
        return users;
    }




}
