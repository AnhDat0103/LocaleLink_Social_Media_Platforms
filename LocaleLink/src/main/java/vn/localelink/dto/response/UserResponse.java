package vn.localelink.DTO.response;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import vn.localelink.enums.Gender;

import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {

    private int id;

    private String email;

    private String fullName;

    private String phone;

    private String address;

    private Gender gender;

    private String avatar;

    private Date dateOfBirth;

    private String status;

}
