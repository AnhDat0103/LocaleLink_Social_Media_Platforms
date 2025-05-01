package vn.localelink.service.serviceImp;

import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.localelink.DTO.response.UserResponse;
import vn.localelink.entity.User;
import vn.localelink.repository.UserRepository;
import vn.localelink.service.UserService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;

    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserResponse findByEmail(String email) {
        User user = userRepository.findByEmail(email);
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getUserId());
        userResponse.setEmail(user.getEmail());
        userResponse.setAddress(user.getAddress());
        userResponse.setFullName(user.getFullName());
        return userResponse;
    }

    @Override
    public List<UserResponse> findAll() {
        return userRepository.findAll().stream().map(user -> {
            UserResponse userResponse = new UserResponse();
            userResponse.setId(user.getUserId());
            userResponse.setEmail(user.getEmail());
            userResponse.setAddress(user.getAddress());
            userResponse.setFullName(user.getFullName());
            return userResponse;
        }).collect(Collectors.toList());
    }
}
