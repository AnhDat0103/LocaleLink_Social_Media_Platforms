package vn.localelink.service.serviceImp;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.localelink.DTO.request.AuthenticateRequest;
import vn.localelink.DTO.response.AuthenticationResponse;
import vn.localelink.entity.User;
import vn.localelink.exception.AppException;
import vn.localelink.service.AuthenticationService;
import vn.localelink.service.UserService;

import static vn.localelink.enums.ErrorEnum.EMAIL_OR_PASSWORD_INCORRECT;

@Service
public class AuhtenticationServiceImp implements AuthenticationService {

    private final UserService userService;
    private final PasswordEncoder encoder;

    public AuhtenticationServiceImp(UserService userService, PasswordEncoder encoder) {
        this.userService = userService;
        this.encoder = encoder;
    }

    private String generateToken(String username) {
        // Implement your token generation logic here
        return "generated_token_for_" + username;
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticateRequest authenticateRequest) throws AppException {
        User user = userService.findByEmail(authenticateRequest.getEmail());

        if(user != null && encoder.matches(authenticateRequest.getPassword(), user.getPassword())) {
            return AuthenticationResponse.builder()
                    .authenticated(true)
                    .email(user.getEmail())
                    .role(user.getRole().getRoleName().toString())
                    .build();
        }
        else {
            throw new AppException(EMAIL_OR_PASSWORD_INCORRECT);
        }

    }

}
