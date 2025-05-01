package vn.localelink.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
@AllArgsConstructor
@Getter
public enum ErrorEnum {
    // 1xxx - validation
    INVALID_FIELDS("1001","Invalid fields"),

    // 2xxx - authentication


    // 3xxx - authorization


    // 4xxx - not found
    USER_NOT_FOUND("4001","User not found");

    // 5xxx - server error


    private final String code;
    private final String message;
}
