package vn.localelink.dto.response;
import lombok.*;
import vn.localelink.entity.enums.Gender;

import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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
