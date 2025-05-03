package vn.localelink.service.serviceImp;

import org.springframework.stereotype.Service;
import vn.localelink.DTO.response.UserResponse;
import vn.localelink.entity.User;
import vn.localelink.enums.ErrorEnum;
import vn.localelink.exception.AppException;
import vn.localelink.mapper.UserMapper;
import vn.localelink.repository.UserRepository;
import vn.localelink.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImp(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }


    @Override
    public UserResponse findByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return userMapper.userToUserResponse(user);
    }

    @Override
    public List<UserResponse> findAll() {
        return userRepository.findAll().stream().map(userMapper::userToUserResponse).collect(Collectors.toList());
    }

    @Override
    public UserResponse findById(int id) throws AppException {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return userMapper.userToUserResponse(user);
        }else {
            throw new AppException(ErrorEnum.USER_NOT_FOUND);
        }
    }
}
