package com.enums;
import lombok.Getter;

@Getter
public enum RESULT{
//
//    PARAM_ERROR(1, "Parameter Error!"),
//    PRODUCT_NOT_EXIST(10, "Product does not exit!"),
//    PRODUCT_NOT_ENOUGH(11, "Not enough products in stock!"),
//    PRODUCT_STATUS_ERROR(12, "Status is incorrect!"),
//    PRODUCT_OFF_SALE(13,"Product is off sale!"),
//    PRODUCT_NOT_IN_CART(14,"Product is not in the cart!"),
//    CART_CHECKOUT_SUCCESS(20, "Checkout successfully! "),
//
    USERS_NOT_FOUND(30, "Users does not exit!"),

    REQUEST_NOT_FOUND(60, "Request is not exit!"),
    REQUEST_STATUS_ERROR(61, "Request Status is not correct"),


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