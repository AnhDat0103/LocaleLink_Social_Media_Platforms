package vn.localelink.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.localelink.dto.response.UserResponse;
import vn.localelink.service.UserService;

import java.util.List;

@Controller()
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/get")
    public String getUserInformation(Model model) {
        String email = "user1@example.com";
        UserResponse user = userService.findByEmail(email);
        model.addAttribute("user", user);
        return "index";
    }

    @GetMapping("/all")
    public String getAllUser(Model model){
        List<UserResponse> users = userService.findAll();
        if(!users.isEmpty()){
            model.addAttribute("users", users);
        }
        return "index";
    }




}
