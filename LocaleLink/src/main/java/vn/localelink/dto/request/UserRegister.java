package vn.localelink.DTO.request;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.localelink.enums.ErrorEnum;
import vn.localelink.enums.Gender;
import vn.localelink.validation.PasswordMatchConstant;


import java.util.Date;





@PasswordMatchConstant(
        field = "password",
        fieldMatch = "confirmPassword",
        message = "Password and Confirm Password do not match"
)
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class UserRegister {

    @Email(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = ErrorEnum.INVALID_EMAIL_MS)
    @NotNull(message = ErrorEnum.NOT_EMPTY_EMAIL)
    private String email;

    @NotNull(message = ErrorEnum.NOT_EMPTY_PASSWORD)
    @Size(min = 5, message = ErrorEnum.INVALID_PASSWORD)
    private String password;

    @NotNull(message = ErrorEnum.NOT_EMPTY_PASSWORD)
    @Size(min = 5, message = ErrorEnum.INVALID_PASSWORD)
    private String confirmPassword;

    @Size(max = 50, message = ErrorEnum.INVALID_NAME)
    @NotNull(message = ErrorEnum.NOT_EMPTY_NAME)
    private String fullName;

    @Size(min = 10, max = 12, message = ErrorEnum.INVALID_PHONE)
    private String phone;

    @Size(max = 255, message = ErrorEnum.INVALID_ADDRESS)
    private String address;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private Date dateOfBirth;


}
