package vn.localelink.entity.enums;

public enum ProviderEnum {
    LOCAL, GOOGLE, FACEBOOK;

    public static ProviderEnum fromString(String provider) {
        for (ProviderEnum p : ProviderEnum.values()) {
            if (p.name().equalsIgnoreCase(provider)) {
                return p;
            }
        }
        return null;
    }
}
