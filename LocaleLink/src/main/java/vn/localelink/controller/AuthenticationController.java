package vn.localelink.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.localelink.DTO.request.AuthenticateRequest;
import vn.localelink.DTO.response.ApiResponse;
import vn.localelink.DTO.response.AuthenticationResponse;
import vn.localelink.DTO.response.JwtVerifyResponse;
import vn.localelink.exception.AppException;
import vn.localelink.service.AuthenticationService;

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
    public ApiResponse<JwtVerifyResponse> verifyToken(@RequestBody String token) throws AppException {
        JwtVerifyResponse jwtVerifyResponse = authenticationService.verifyToken(token);
        return ApiResponse.<JwtVerifyResponse>builder()
                .status("success")
                .code("200")
                .message("Authenticated.")
                .data(jwtVerifyResponse)
                .build();
    }

}
