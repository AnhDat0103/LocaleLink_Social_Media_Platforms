package vn.localelink.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import vn.localelink.enums.RoleEnum;

@Entity
@Table(name = "roles")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Role {
    @Id()
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roleId;

    @Column(name = "name", unique = true)
    @NotNull(message = "Role name cannot be null")
    @Enumerated(EnumType.STRING)
    private RoleEnum roleName;

    @Lob
    @Column(name = "description")
    private String description;

}
