package vn.localelink.service.serviceImp;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.localelink.DTO.request.AuthenticateRequest;
import vn.localelink.DTO.response.AuthenticationResponse;
import vn.localelink.entity.User;
import vn.localelink.exception.AppException;
import vn.localelink.service.AuthenticationService;
import vn.localelink.service.UserService;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;


import static vn.localelink.enums.ErrorEnum.EMAIL_OR_PASSWORD_INCORRECT;

@Service
@Slf4j
public class AuhtenticationServiceImp implements AuthenticationService {

    private final UserService userService;
    private final PasswordEncoder encoder;
    public static final JWSAlgorithm JWS_ALGORITHM = JWSAlgorithm.HS512;

    @Value("${jwt.secret_key}")
    public String secretKey;

    @Value("${jwt.expiration_time}")
    public long expirationTime;

    public AuhtenticationServiceImp(UserService userService, PasswordEncoder encoder) {
        this.userService = userService;
        this.encoder = encoder;
    }

    private String generateToken(String email) {
        try {
            JWSHeader jwsHeader = new JWSHeader(JWS_ALGORITHM);

            JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                    .issuer("LocaleLink")
                    .subject(email)
                    .issueTime(new Date())
                    .expirationTime(new Date(
                            Instant.now().plus(expirationTime, ChronoUnit.SECONDS).toEpochMilli()
                    ))
                    .claim("role", "USER" )
                    .build();
            Payload payload = new Payload(jwtClaimsSet.toJSONObject());

            JWSObject jwsObject = new JWSObject(jwsHeader, payload);
            jwsObject.sign(new MACSigner(secretKey.getBytes(StandardCharsets.UTF_8)));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.error("Failed to create token for user: {}", email, e);
            throw new AuthenticationServiceException("Failed to create token for user: " + email, e);
        }
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticateRequest authenticateRequest) throws AppException {
        User user = userService.findByEmail(authenticateRequest.getEmail());

        if(encoder.matches(authenticateRequest.getPassword(), user.getPassword())) {
            String token = generateToken(user.getEmail());
            return AuthenticationResponse.builder()
                    .token(token)
                    .build();
        } else {
            throw new AppException(EMAIL_OR_PASSWORD_INCORRECT);
        }

    }

}
