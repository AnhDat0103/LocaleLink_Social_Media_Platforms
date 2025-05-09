package vn.localelink.service.serviceImp;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import vn.localelink.entity.User;
import vn.localelink.enums.ErrorEnum;
import vn.localelink.exception.AppException;
import vn.localelink.service.UserService;

import java.util.Collections;

@Component("userDetailsService")
public class UserDetailsServiceCustom implements UserDetailsService {

    private final UserService userService;

    public UserDetailsServiceCustom(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User user = userService.findByEmail(username);
            return new org.springframework.security.core.userdetails.User(
                    user.getEmail(),
                    user.getPassword(),
                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
            );

        }catch (AppException e) {
            if (e.getErrorCode().equals(ErrorEnum.USER_NOT_FOUND.getCode())) {
                throw new UsernameNotFoundException("User not found with email: " + username);
            } else {
                throw new RuntimeException("An error occurred while loading user by username", e);
            }
        }
    }
}
