package vn.localelink.enums;

import lombok.Getter;

@Getter
public enum RoleEnum {
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN"),
    MODERATOR("ROLE_MODERATOR");

    private final String role;

    RoleEnum(String role) {
        this.role = role;
    }

    public static RoleEnum fromString(String role) {
        for (RoleEnum r : RoleEnum.values()) {
            if (r.getRole().equalsIgnoreCase(role)) {
                return r;
            }
        }
        return null;
    }
}
