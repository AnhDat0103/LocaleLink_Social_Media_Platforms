package vn.localelink.exception;

import lombok.Getter;
import lombok.Setter;
import vn.localelink.enums.ErrorEnum;

@Getter
@Setter
public class AppException  extends Exception {

    private String errorCode;

    public AppException(ErrorEnum errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode.getCode();
    }

}
