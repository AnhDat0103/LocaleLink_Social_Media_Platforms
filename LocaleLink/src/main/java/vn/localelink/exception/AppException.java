package vn.localelink.exception;

import lombok.Getter;
import lombok.Setter;
import vn.localelink.enums.ErrorEnum;

import java.util.List;

@Getter
@Setter
public class AppException  extends Exception {

    private String errorCode;
    private List<FieldValidationError> fieldValidationErrors;

    public AppException(ErrorEnum errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode.getCode();
    }

    public AppException(ErrorEnum errorEnum,List<FieldValidationError> fieldValidationErrors) {
        super(errorEnum.getMessage());
        this.errorCode = errorEnum.getCode();
        this.fieldValidationErrors = fieldValidationErrors;
    }


}
