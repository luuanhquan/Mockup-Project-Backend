package com.enums;

public enum EMAIL_TYPE {
    FORGOT(0),
    ACTIVE(1);

    public final int value;

    EMAIL_TYPE(int value) {
        this.value = value;
    }

    public static EMAIL_TYPE valueOf(Integer value) {
        if (value == null) {
            return null;
        }

        for (EMAIL_TYPE val : EMAIL_TYPE.values()) {
            if (val.value == value) {
                return val;
            }
        }

        return null;
    }

}
