package vn.localelink.entity.enums;

public enum Gender {
    MALE, FEMALE, OTHER;

    public static Gender getGender(String gender) {
        for (Gender g : Gender.values()) {
            if(g.toString().equalsIgnoreCase(gender)) {
                return g;
            }
        }
        return OTHER;
    }
}
