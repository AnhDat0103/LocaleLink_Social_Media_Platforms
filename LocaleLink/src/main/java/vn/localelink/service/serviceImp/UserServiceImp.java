package vn.localelink.service.serviceImp;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.localelink.DTO.request.UserRegister;
import vn.localelink.DTO.response.UserResponse;

import vn.localelink.entity.Role;
import vn.localelink.entity.User;
import vn.localelink.enums.ErrorEnum;
import vn.localelink.enums.ProviderEnum;
import vn.localelink.enums.RoleEnum;
import vn.localelink.enums.StatusEnum;
import vn.localelink.exception.AppException;
import vn.localelink.mapper.UserMapper;
import vn.localelink.repository.UserRepository;
import vn.localelink.service.RoleService;
import vn.localelink.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder encoder;
    private final RoleService roleService;

    public UserServiceImp(UserRepository userRepository, UserMapper userMapper, PasswordEncoder encoder, RoleService roleService) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.encoder = encoder;
        this.roleService = roleService;
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

    @Override
    public UserResponse createUser(UserRegister userRegister) throws AppException {
        if(userRepository.existsByEmail(userRegister.getEmail())){
            throw new AppException(ErrorEnum.EMAIL_EXIST);
        } else if(userRepository.existsByPhone(userRegister.getPhone())){
            throw new AppException(ErrorEnum.PHONE_EXIST);
        }else if(!userRegister.getPassword().equals(userRegister.getConfirmPassword())){
            throw new AppException(ErrorEnum.PASSWORD_NOT_MATCH);
        }else{
            User user = userMapper.userRegisterToUser(userRegister);
            user.setPassword(encoder.encode(user.getPassword()));
            user.setProvider(ProviderEnum.LOCAL);
            user.setStatus(StatusEnum.PENDING);
            user.setRole(roleService.findRoleByName(RoleEnum.USER));
            userRepository.save(user);
            return userMapper.userToUserResponse(user);
        }
    }
}
