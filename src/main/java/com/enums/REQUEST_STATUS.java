package com.enums;

public enum REQUEST_STATUS {
    PENDING(0),
    APPROVE(1),
    DENIED(2),
    CANCELED(3);

    public final int value;

    REQUEST_STATUS(int value) {
        this.value = value;
    }

    public static REQUEST_STATUS valueOf(Integer value) {
        if (value == null) {
            return null;
        }

        for (REQUEST_STATUS val : REQUEST_STATUS.values()) {
            if (val.value == value) {
                return val;
            }
        }

        return null;
    }

}
