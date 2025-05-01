package vn.localelink.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApiErrorResponse<T> {
    private final String status = "error";
    private String message;
    private List<T> data;
}
