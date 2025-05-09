package vn.localelink.service;

import com.nimbusds.jose.JOSEException;
import vn.localelink.DTO.request.AuthenticateRequest;
import vn.localelink.DTO.request.IntrospectRequest;
import vn.localelink.DTO.response.AuthenticationResponse;
import vn.localelink.DTO.response.IntrospectResponse;
import vn.localelink.exception.AppException;

import java.text.ParseException;

public interface AuthenticationService {

    AuthenticationResponse authenticate(AuthenticateRequest authenticateRequest) throws AppException;


    IntrospectResponse verifyToken(IntrospectRequest introspectRequest) throws JOSEException, ParseException;
}
