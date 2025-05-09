package vn.localelink.controller;

import com.nimbusds.jose.JOSEException;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.localelink.DTO.request.AuthenticateRequest;
import vn.localelink.DTO.request.IntrospectRequest;
import vn.localelink.DTO.response.ApiResponse;
import vn.localelink.DTO.response.AuthenticationResponse;
import vn.localelink.DTO.response.IntrospectResponse;
import vn.localelink.exception.AppException;
import vn.localelink.service.AuthenticationService;

import java.text.ParseException;

@RestController()
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/authenticate")
    public ApiResponse<AuthenticationResponse> authenticate(@Valid @RequestBody AuthenticateRequest authenticateRequest) throws AppException {
        AuthenticationResponse authenticationResponse = authenticationService.authenticate(authenticateRequest);
        return ApiResponse.<AuthenticationResponse>builder()
                .status("success")
                .code("200")
                .message("Authenticated.")
                .data(authenticationResponse)
                .build();
    }

    @PostMapping("/verify-token")
    public ApiResponse<IntrospectResponse> verifyToken(@RequestBody IntrospectRequest introspectRequest) throws ParseException, JOSEException {
        IntrospectResponse introspectResponse = authenticationService.verifyToken(introspectRequest);
        return ApiResponse.<IntrospectResponse>builder()
                .status("success")
                .code("200")
                .message(introspectResponse.isValid() ? "Token is valid." : "Token is invalid.")
                .data(introspectResponse)
                .build();
    }

}
