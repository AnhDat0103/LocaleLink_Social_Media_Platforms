package vn.localelink.DTO.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.localelink.exception.FieldValidationError;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class ApiErrorResponse<T> {
    private final String status = "error";
    private String code;
    private String message;
    private List<T> data;
}
