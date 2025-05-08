package vn.localelink.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vn.localelink.DTO.request.AuthenticateRequest;
import vn.localelink.DTO.response.ApiResponse;
import vn.localelink.DTO.response.AuthenticationResponse;
import vn.localelink.exception.AppException;
import vn.localelink.service.AuthenticationService;

@RestController()
public class AuthenticationController {

    private final AuthenticationService authenticationService;


    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ApiResponse<AuthenticationResponse> authenticate(@Valid @RequestBody AuthenticateRequest authenticateRequest) throws AppException {
        AuthenticationResponse authenticationResponse = authenticationService.authenticate(authenticateRequest);
        return ApiResponse.<AuthenticationResponse>builder()
                .status("success")
                .code("200")
                .message("Authenticated.")
                .data(authenticationResponse)
                .build();
    }

}
