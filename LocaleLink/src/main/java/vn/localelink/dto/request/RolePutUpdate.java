package vn.localelink.DTO.request;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.localelink.enums.RoleEnum;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RolePutUpdate {
    @NotNull(message = "Role name cannot be null")
    @Enumerated(EnumType.STRING)
    private RoleEnum roleName;

    @Lob
    @Column(name = "description")
    private String description;
}
