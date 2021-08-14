package com.enums;

public enum ISSUE_TARGET {
    BACKEND(0),
    FRONTEND(1),
    ANDROID(2),
    IOS(3);


    public final int value;

    ISSUE_TARGET(int value) {
        this.value = value;
    }

    public static ISSUE_TARGET valueOf(Integer value) {
        if (value == null) {
            return null;
        }

        for (ISSUE_TARGET val : ISSUE_TARGET.values()) {
            if (val.value == value) {
                return val;
            }
        }

        return null;
    }

}
