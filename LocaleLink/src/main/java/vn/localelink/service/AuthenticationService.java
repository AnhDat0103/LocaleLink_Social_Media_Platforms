package vn.localelink.service;

import vn.localelink.DTO.request.AuthenticateRequest;
import vn.localelink.DTO.response.AuthenticationResponse;
import vn.localelink.exception.AppException;

public interface AuthenticationService {

    AuthenticationResponse authenticate(AuthenticateRequest authenticateRequest) throws AppException;

}
