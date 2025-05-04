package vn.localelink.DTO.request;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.localelink.enums.ErrorEnum;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserAuthenticate {

    @Email(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = ErrorEnum.INVALID_EMAIL_MS)
    private String email;
    private String password;
}
