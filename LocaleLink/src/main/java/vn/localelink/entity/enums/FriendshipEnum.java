package vn.localelink.entity.enums;

public enum FriendshipEnum {
    PENDING, ACCEPTED, BLOCKED;

    public static FriendshipEnum fromString(String status) {
        for (FriendshipEnum s : FriendshipEnum.values()) {
            if (s.name().equalsIgnoreCase(status)) {
                return s;
            }
        }
        return null;
    }
}
