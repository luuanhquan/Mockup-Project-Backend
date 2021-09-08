package com.enums;
import lombok.Getter;

@Getter
public enum RESULT{

    USERNAME_NOT_EXIST(10, "Username is already taken!"),
    EMAIL_NOT_EXIST(11, " Email is already in use!"),

    USERS_NOT_FOUND(30, "Users does not exit!"),

    REQUEST_NOT_FOUND(60, "Request is not exit!"),
    USER_REQUEST_NOT_FOUND(61, "UserRequest is not exit!"),
    REQUEST_STATUS_ERROR(62, "Request Status is not correct"),
    REQUEST_NOT_USER(63, "Request User is not exit"),


    VALID_ERROR(50, "Wrong information"),
    USER_NOT_FOUNT(40,"User is not found!")
    ;

    private Integer code;

    private String message;

    RESULT(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}