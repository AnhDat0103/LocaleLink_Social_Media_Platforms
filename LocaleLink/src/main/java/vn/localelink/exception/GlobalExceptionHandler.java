package vn.localelink.exception;


import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import vn.localelink.DTO.response.ApiErrorResponse;
import vn.localelink.enums.ErrorEnum;


@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(AppException.class)
    public ResponseEntity<ApiErrorResponse> handleNotFoundException(AppException exception) {
        ApiErrorResponse errorResponse = ApiErrorResponse.builder()
                .code(exception.getErrorCode())
                .message(exception.getMessage())
                .build();
        return ResponseEntity.badRequest().body(errorResponse);



    }

}
