package com.exception;

import com.enums.RESULT;

public class MyException extends RuntimeException {

    private Integer code;

    public MyException(RESULT resultEnum) {
        super(resultEnum.getMessage());

        this.code = resultEnum.getCode();
    }

    public MyException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
