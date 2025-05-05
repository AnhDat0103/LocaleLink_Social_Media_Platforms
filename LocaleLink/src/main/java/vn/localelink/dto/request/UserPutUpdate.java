package vn.localelink.DTO.request;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.localelink.enums.Gender;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserPutUpdate {

    @Size(max = 50, message = "Full name must be less than 50 characters")
    @NotNull(message = "Full name cannot be null")
    private String fullName;

    @Size(min = 10, max = 12, message = "Phone number must be between 10 and 12 digits")
    private String phone;

    @Size(max = 255, message = "Address must be less than 255 characters")
    private String address;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Size(max = 255, message = "Avatar URL must be less than 255 characters")
    private String avatar;

    private Date dateOfBirth;

    private Date updatedAt;
}
