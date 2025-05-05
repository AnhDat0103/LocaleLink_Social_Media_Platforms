package vn.localelink.exception;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import vn.localelink.DTO.response.ApiErrorResponse;
import vn.localelink.enums.ErrorEnum;

import java.util.List;



@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(AppException.class)
    public ResponseEntity<ApiErrorResponse> handleNotFoundException(AppException exception) {
        ApiErrorResponse errorResponse = ApiErrorResponse.builder()
                .code(exception.getErrorCode())
                .message(exception.getMessage())
                .build();
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleArgumentNotValidationException(MethodArgumentNotValidException exception) {

        // Extract field validation errors
        List<FieldValidationError> errors = new java.util.ArrayList<>(exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(e -> new FieldValidationError(e.getField(), e.getDefaultMessage()))
                .toList());


        // Add global errors to the list
        var globalErrors = exception.getBindingResult()
                .getGlobalErrors()
                .stream()
                .map(e -> new FieldValidationError(e.getObjectName(), e.getDefaultMessage()))
                .toList();
        errors.addAll(globalErrors);

        ApiErrorResponse errorResponse = ApiErrorResponse.<FieldValidationError>builder()
                .code(ErrorEnum.INVALID_FIELDS.getCode())
                .message(ErrorEnum.INVALID_FIELDS.getMessage())
                .data(errors)
                .build();
        return ResponseEntity.badRequest().body(errorResponse);
    }

}
