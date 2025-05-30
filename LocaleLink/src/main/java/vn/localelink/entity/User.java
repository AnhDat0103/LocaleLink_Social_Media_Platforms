package vn.localelink.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import vn.localelink.enums.Gender;
import vn.localelink.enums.ProviderEnum;
import vn.localelink.enums.StatusEnum;

import java.util.Date;

@Entity
@Table(name = "user_profiles")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    @Column(name = "email", unique = true)
    @Email(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Invalid email format")
    @NotNull(message = "Email cannot be null")
    private String email;

    @Column(name = "password")
    @NotNull(message = "Password cannot be null")
    @Size(min = 5, message = "Password must be at least 5 characters long")
    private String password;

    @Column(name = "full_name")
    @Size(max = 50, message = "Full name must be less than 50 characters")
    @NotNull(message = "Full name cannot be null")
    private String fullName;

    @Column(name = "phone" , unique = true)
    @Size(min = 10, max = 12, message = "Phone number must be between 10 and 12 digits")
    private String phone;

    @Column(name = "address")
    @Size(max = 255, message = "Address must be less than 255 characters")
    private String address;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "avatar")
    @Size(max = 255, message = "Avatar URL must be less than 255 characters")
    private String avatar;

    @Column(name = "birth_of_date")
    private Date dateOfBirth;

    @Column(name = "create_at")
    private Date createdAt;

    @Column(name = "update_at")
    private Date updatedAt;

    @Column(name = "provider")
    @Enumerated(EnumType.STRING)
    private ProviderEnum provider;

    @Lob
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "role_id")
    private Role role;

}
