package vn.localelink.DTO.response;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIncludeProperties
public class ApiResponse<T> {

    private String status;
    private String code;
    private String message;
    private T data;
}
