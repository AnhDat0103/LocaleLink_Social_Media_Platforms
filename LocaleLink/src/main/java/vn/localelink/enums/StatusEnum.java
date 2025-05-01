package vn.localelink.enums;

public enum StatusEnum {
    ACTIVE, INACTIVE, PENDING, BANNED;

    public StatusEnum fromString(String value) {
        for (StatusEnum s : StatusEnum.values()) {
            if(s.name().equalsIgnoreCase(value)){
                return s;
            }
        }
        return null;
    }
}
